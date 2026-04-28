package com.techbytes.qa.utilities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ApiUtils {

    private static final Logger logger = LoggerFactory.getLogger(ApiUtils.class);

    //    Shared HTTP CLIENT
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    private ApiUtils() {
    }

    public static int getStatusCode(String url) throws IOException, InterruptedException {
        logger.info(" -> Getting Status Code for url: {}", url);
        logger.debug(" -> Creating HttpRequest for the url.");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();
        logger.debug(" -> Creating HttpResponse for the url.");
        HttpResponse<Void> response = CLIENT.send(request, HttpResponse.BodyHandlers.discarding());
        logger.debug(" -> Returning the status code.");
        return response.statusCode();
    }

    public static int getAuthenticatedStatusCode(String url, String username, String password) throws IOException, InterruptedException {
        logger.info(" -> Getting Authntication Status Code for url: {}", url);
        // Apache HttpClient handles the nonce/handshake internally
        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials(username, password.toCharArray()));

        try (CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(provider).build()) {
            HttpGet request = new HttpGet(url);
            return client.execute(request, response -> response.getCode());
        }

//        // 1. Initial request to trigger challenge
//        HttpRequest initRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
//        HttpResponse<String> initResponse = CLIENT.send(initRequest, HttpResponse.BodyHandlers.ofString());
//        if (initResponse.statusCode() == 401) {
//            String authHeader = initResponse.headers().firstValue("WWW-Authenticate").orElse("");
//
//            // 2. Parse authHeader for nonce, realm, etc.
//            // 3. Calculate Digest response (Requires MD5 hashing logic)
//            String digestHeader = calculateDigestHeader(authHeader, "admin", "admin", "GET", "/digest_auth");
//
//            // 4. Final request with Digest header
//            HttpRequest finalRequest = HttpRequest.newBuilder()
//                    .uri(URI.create(url))
//                    .header("Authorization", digestHeader)
//                    .GET()
//                    .build();
//
//            return CLIENT.send(finalRequest, HttpResponse.BodyHandlers.ofString()).statusCode();
//        }
//        String rawAuth = username + ":" + password;
//        logger.info("Encrypting Authorization for user: {}.", username);
//        String encodedAuth = Base64.getEncoder().encodeToString(rawAuth.getBytes(StandardCharsets.UTF_8));
//        logger.debug("Creating HttpRequest for the url.");
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .timeout(Duration.ofSeconds(10))
//                .header("Authorization", "Basic " + encodedAuth)
//                .GET()
//                .build();
//        logger.debug("Creating HttpResponse for the url.");
//        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
//        logger.debug("Returning the status code.");
//        return response.statusCode();
    }

    public static int waitForStatusCode(String url, int expectedStatus, int timeoutSeconds, int pollMillis) throws Exception {
        logger.info(" -> Getting Status Code for url: {}", url);
        logger.debug(" -> Setting end time in seconds.");
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);
        logger.debug(" -> Requesting every {} seconds.", pollMillis);
        int lastStatus = -1;
        while (System.currentTimeMillis() < endTime) {
            logger.info(" -> Requesting Status Code");
            lastStatus = getStatusCode(url);
            if (lastStatus == expectedStatus) {
                return lastStatus;
            }
            Thread.sleep(pollMillis);
        }
        return lastStatus;
    }

    public static String getResponseBody(String url) throws IOException, InterruptedException {
        logger.info(" -> Converting the body of the Response to string");
        logger.debug(" -> Creating the HttpRequest for url: {}", url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();
        logger.debug(" -> Creating the HttpResponse from the request.");
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        logger.debug(" -> Returning the body of the response.");
        return response.body();
    }

    public static boolean waitForTextInResponse(String url, String expectedText, int timeoutSeconds, int pollingMillis) throws IOException, InterruptedException {
        logger.info(" -> Getting Status Code after waiting for text in the response for url: {}", url);
        logger.debug(" -> Setting end time in seconds.");
        long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);
        logger.debug(" -> Requesting every {} seconds.", pollingMillis);
        while (System.currentTimeMillis() < endTime) {
            String body = getResponseBody(url);
            logger.debug(" -> Checking if the response body contains the expected Message.");
            if (body != null && body.contains(expectedText)) {
                logger.debug("Expected Message found. Returning TRUE.");
                return true;
            }
            Thread.sleep(pollingMillis);
        }
        logger.debug(" -> Expected Message not found. Returning FALSE.");
        return false;
    }

}
