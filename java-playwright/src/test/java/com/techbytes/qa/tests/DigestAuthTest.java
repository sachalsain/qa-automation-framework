package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.DigestAuthPage;
import com.techbytes.qa.utilities.TestDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.IOException;
import java.util.Map;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DigestAuthTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthTest.class);

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Digest AUTH testing")
    @Description("Digest AUTH testing using valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Tahreem J. Naseem")
    public void digestAuthTest(Map<String, String> data) {
        logger.info("Testing Digest Auth");
//        logger.debug("Setting up Auth Playwright instance.");
//        setupWithAuth(data.get("username"), data.get("password"));
        logger.debug("Creating DigestAuthPage instance.");
        DigestAuthPage authPage = new DigestAuthPage(page);
//        logger.debug("Opening page.");
//        int status = authPage.open();
        // Apache HttpClient handles the nonce/handshake internally
        int status = 0;
        BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials(data.get("username"), data.get("password").toCharArray()));
        try (CloseableHttpClient client = HttpClients.custom().setDefaultCredentialsProvider(provider).build()) {
            HttpGet request = new HttpGet(authPage.getUrl());
            status = client.execute(request, response -> response.getCode());
            logger.debug("Successfully received the status code: {}.", status);
        } catch (IOException ex) {
            logger.error("Authentication Request failed. Reason: {}", ex.getMessage());
        }
        if (data.get("expected").equals("pass")) {
                logger.debug("Verifying that the status is 200.");
            Assert.assertEquals(200, status, "The response status should be 200 for proper credentials");
                logger.debug("Verified that the status is 200.");
        } else {
                logger.debug("Verifying that the status is 401.");
            Assert.assertEquals(401, status, "The response status should be 401 for unauthorized credentials");
                logger.debug("Verified that the status is 401.");
        }
    }
}
