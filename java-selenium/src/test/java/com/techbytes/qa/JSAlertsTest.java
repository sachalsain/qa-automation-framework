package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.JSAlertsPage;
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
public class JSAlertsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(JSAlertsTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing JavaScript Alerts Page")
    @Description("Testing JavaScript Alerts Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void jSAlertsTest() {
        logger.info(" -> Starting Test of JavaScript Alerts Page");
        logger.debug(" -> Creating JSAlertsPage instance.");
        JSAlertsPage alertsPage = new JSAlertsPage(getDriver());
        logger.debug(" -> Opening JavaScript Alerts Page.");
        alertsPage.open();
        
        logger.debug(" -> Clicking Alert Button to test.");
        String message = alertsPage.clickAlertBtn();
        
        logger.debug(" -> Verifying if Alert message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS Alert"), "The message must display 'I am a JS Alert'");
        logger.debug(" -> Verified that Alert message is matching.");
        
        logger.debug(" -> Verifying if proper Alert Result is shown.");
        Assert.assertTrue(alertsPage.getResult().equalsIgnoreCase("You successfully clicked an alert"), "The message must display 'You successfully clicked an alert'");
        logger.debug(" -> Verified that proper Alert Result is shown.");
        
        logger.debug("   -> Clicking Confirm Button to test OK.");
        message = alertsPage.clickConfirmBtn("OK");
        
        logger.debug(" -> Verifying if Confirm message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS Confirm"), "The message must display 'I am a JS Confirm'");
        logger.debug(" -> Verified that Confirm message is matching.");
        
        logger.debug(" -> Verifying if proper Confirm Result is shown.");
        Assert.assertTrue(alertsPage.getResult().equalsIgnoreCase("You clicked: Ok"), "The message must display 'You clicked: Ok'");
        logger.debug(" -> Verified that proper Confirm Result is shown.");
        
        logger.debug(" -> Clicking Confirm Button to test Cancel.");
        message = alertsPage.clickConfirmBtn("Cancel");
        
        logger.debug(" -> Verifying if Confirm message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS Confirm"), "The message must display 'I am a JS Confirm'");
        logger.debug(" -> Verified that Confirm message is matching.");
        
        logger.debug(" -> Verifying if proper Confirm Result is shown.");
        Assert.assertTrue(alertsPage.getResult().equalsIgnoreCase("You clicked: Cancel"), "The message must display 'You clicked: Cancel'");
        logger.debug(" -> Verified that proper Confirm Result is shown.");
        
        logger.debug(" -> Clicking Prompt Button to test OK.");
        message = alertsPage.clickPromptBtn("OK");
        
        logger.debug(" -> Verifying if Prompt message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS prompt"), "The message must display 'I am a JS prompt'");
        logger.debug(" -> Verified that Prompt message is matching.");
        
        logger.debug(" -> Verifying if proper Prompt Result is shown.");
        Assert.assertTrue(alertsPage.getResult().equalsIgnoreCase("You entered: Playwright Java"), "The message must display 'You entered: Playwright Java'");
        logger.debug(" -> Verified that proper Prompt Result is shown.");
        
        logger.debug(" -> Clicking Prompt Button to test Cancel.");
        message = alertsPage.clickPromptBtn("Cancel");
        
        logger.debug(" -> Verifying if Prompt message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS prompt"), "The message must display 'I am a JS prompt'");
        logger.debug(" -> Verified that Prompt message is matching.");
        
        logger.debug(" -> Verifying if proper Prompt Result is shown.");
        Assert.assertTrue(alertsPage.getResult().equalsIgnoreCase("You entered: null"), "The message must display 'You entered: null'");
        logger.debug(" -> Verified that proper Prompt Result is shown.");
    }
    
}
