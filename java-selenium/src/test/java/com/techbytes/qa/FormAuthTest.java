package com.techbytes.qa;

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

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class, retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Form Authentication Page")
    @Description("Testing Form Authentication Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void formAuthTest(Map<String, String> data) {
        logger.info(" -> Starting Test of Form Authentication Page");
        logger.debug(" -> Creating FormAuthPage instance.");
        FormAuthPage formAuthPage = new FormAuthPage(getDriver());
        logger.debug(" -> Opening Form Authentication Page.");
        formAuthPage.open();
        logger.debug(" -> Performing Login.");
        formAuthPage.login(data.get("username"), data.get("password"));
        logger.debug(" -> Retreiving message.");
        if (data.get("expected").equals("success")) {
            // Not Null Assert
            logger.debug(" -> Verifying if message is not null.");
            Assert.assertNotNull(formAuthPage.getMessage());
            logger.debug(" -> Verified that message is not null.");
            // Hard Assert
            logger.debug(" -> Verifying if message conatins SUCCESS.");
            Assert.assertTrue(formAuthPage.getMessage().contains("secure area"), "Login success message should be shown");
            logger.debug(" -> Verified that message conatins SUCCESS.");
            // Equality Assert
            logger.debug(" -> Verifying if message is equal to expected.");
            Assert.assertEquals(formAuthPage.getMessage().contains("secure area"), true);
            logger.debug(" -> Verified that message is equal to expected.");
        } else {
            logger.debug(" -> Validating error message...");
            // Negative assertion
            logger.debug(" -> Verifying if message is displayed.");
            Assert.assertTrue(formAuthPage.isErrorVisible(), "Invalid login error should appear");
            logger.debug(" -> Verified that message is displayed.");
            
            logger.debug(" -> Verifying if message does not contain SUCCESS.");
            Assert.assertFalse(formAuthPage.getMessage().contains("Success"), "The message should not read 'Success'");
            logger.debug(" -> Verified that message does not contain SUCCESS.");
        }
    }
    
}
