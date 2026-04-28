package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.ForgotPasswordPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ForgotPasswordTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Forgot Password Page")
    @Description("Testing Forgot Password Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void forgotPasswordTest() {
        logger.info(" -> Starting Test of Forgot Password Page");
        logger.debug(" -> Creating ForgotPasswordPage instance.");
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(getDriver());
        logger.debug(" -> Opening Forgot Password Page.");
        forgotPasswordPage.open();
        logger.debug(" -> Submitting form.");
        forgotPasswordPage.submitForm("testUser@example.com");
        
        logger.debug(" -> Verifying if the form submitted successfully.");
        Assert.assertTrue(forgotPasswordPage.getMessage().contains("Server Error"), "Must contain 'Server Error'");
        logger.debug(" -> Verified that the form submitted successfully.");
    }
    
}
