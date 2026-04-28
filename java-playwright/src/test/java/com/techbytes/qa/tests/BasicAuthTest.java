package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.BasicAuthPage;
import com.techbytes.qa.utilities.TestDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class BasicAuthTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthTest.class);

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Basic AUTH testing")
    @Description("Basic AUTH testing using valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Tahreem J. Naseem")
    public void basicAuthTest(Map<String, String> data) {
        logger.info("Testing Basic Auth");
        logger.debug("Setting up Auth Playwright instance.");
        setupWithAuth(data.get("username"), data.get("password"));
        logger.debug("Creating BasicAuthPage instance.");
        BasicAuthPage authPage = new BasicAuthPage(page);
        logger.debug("Opening page.");
        int status = authPage.open();
        logger.debug("Successfully received the status code: {}.", status);
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
