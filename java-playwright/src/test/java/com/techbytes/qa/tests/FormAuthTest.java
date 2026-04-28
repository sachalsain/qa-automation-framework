package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.FormAuthPage;
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

public class FormAuthTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FormAuthTest.class);

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class, groups="regression")
    @Story("User logs in with valid credentials")
    @Description("Verify successful login using valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Tahreem J. Naseem")
    public void loginTest(Map<String, String> data) {
        logger.info("Testing Login");
        logger.debug("Creating LoginPage instance.");
        FormAuthPage loginPage = new FormAuthPage(page);
        logger.debug("Opening Login Page.");
        loginPage.open();
        logger.debug("Performing Login.");
        loginPage.login(data.get("username"), data.get("password"));
        logger.debug("Retreiving message.");
        if (data.get("expected").equals("success")) {
            logger.debug("Validating success message.");
            // Not Null Assert
            logger.debug("Validating if message is not null.");
            Assert.assertNotNull(loginPage.getMessage());
            // Hard Assert
            logger.debug("Validating if message conatins SUCCESS.");
            Assert.assertTrue(loginPage.getMessage().contains("secure area"), "Login success message should be shown");
            // Equality Assert
            logger.debug("Validating if message is equal to expected.");
            Assert.assertEquals(loginPage.getMessage().contains("secure area"), true);
        } else {
            logger.debug(" -> Validating error message...");
            // Negative assertion
            logger.debug("Validating if message is displayed.");
            Assert.assertTrue(loginPage.isErrorVisible(), "Invalid login error should appear");
            logger.debug("Validating if message does not contain SUCCESS.");
            Assert.assertFalse(loginPage.getMessage().contains("Success"), "The message should not read 'Success'");
        }
    }
}
