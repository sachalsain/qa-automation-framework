package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.DigestAuthPage;
import com.techbytes.qa.utilities.ApiUtils;
import com.techbytes.qa.utilities.TestDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DigestAuthTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DigestAuthTest.class);

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Digest Authentication Page")
    @Description("Testing Digest Authentication Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void digestAuthTest(Map<String, String> data) {
        logger.info(" -> Starting Test of Digest Authentication Page");
        logger.debug(" -> Creating DigestAuthPage instance.");
        DigestAuthPage digestAuthPage = new DigestAuthPage(getDriver());
//        logger.debug("Opening DigestAuthPage Page.");
//        digestAuthPage.open();
        try {
            logger.debug(" -> Requesting the status code.");
            int status = ApiUtils.getAuthenticatedStatusCode(digestAuthPage.getUrl(), data.get("username"), data.get("password"));
            logger.debug(" -> Successfully received the status code: {}.", status);
            if (data.get("expected").equals("pass")) {
                logger.debug(" -> Verifying that the status is 200.");
                Assert.assertEquals(200, status, "The response status should be 200 but found " + status);
                logger.debug(" -> Verified that the status is 200.");
            } else {
                logger.debug(" -> Verifying that the status is 401.");
                Assert.assertEquals(401, status, "The response status should be 401 but found " + status);
                logger.debug(" -> Verified that the status is 401.");
            }
        } catch (IOException | InterruptedException ex) {
            logger.error(" -> Authentication Request failed. Reason: {}", ex.getMessage());
        }
    }
}
