package com.techbytes.qa.tests;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("JSAlerts Test.")
    @Description("Test to check JavaScripts Alerts work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void jSAlertsTest() {
        logger.info("Testing JavaScripts Alerts");
        logger.debug("Creating jSAlertsPage instance.");
        JSAlertsPage jSAlertsPage = new JSAlertsPage(page);
        logger.debug("Opening JavaScripts Alerts Page.");
        jSAlertsPage.open();
        
        logger.debug("Clicking Alert Button to test.");
        String message = jSAlertsPage.clickAlertBtn();
        logger.debug("Checking if Alert message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS Alert"), "The message must display 'I am a JS Alert'");
        logger.debug("Checking if proper Alert Result is shown.");
        Assert.assertTrue(jSAlertsPage.getResult().equalsIgnoreCase("You successfully clicked an alert"), "The message must display 'You successfully clicked an alert'");
        
        logger.debug("Clicking Confirm Button to test OK.");
        message = jSAlertsPage.clickConfirmBtn("OK");
        logger.debug("Checking if Confirm message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS Confirm"), "The message must display 'I am a JS Confirm'");
        logger.debug("Checking if proper Confirm Result is shown.");
        Assert.assertTrue(jSAlertsPage.getResult().equalsIgnoreCase("You clicked: Ok"), "The message must display 'You clicked: Ok'");
        
        logger.debug("Clicking Confirm Button to test Cancel.");
        message = jSAlertsPage.clickConfirmBtn("Cancel");
        logger.debug("Checking if Confirm message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS Confirm"), "The message must display 'I am a JS Confirm'");
        logger.debug("Checking if proper Confirm Result is shown.");
        Assert.assertTrue(jSAlertsPage.getResult().equalsIgnoreCase("You clicked: Cancel"), "The message must display 'You clicked: Cancel'");
        
        logger.debug("Clicking Prompt Button to test OK.");
        message = jSAlertsPage.clickPromptBtn("OK");
        logger.debug("Checking if Prompt message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS prompt"), "The message must display 'I am a JS prompt'");
        logger.debug("Checking if proper Prompt Result is shown.");
        Assert.assertTrue(jSAlertsPage.getResult().equalsIgnoreCase("You entered: Playwright Java"), "The message must display 'You entered: Playwright Java'");
        
        logger.debug("Clicking Prompt Button to test Cancel.");
        message = jSAlertsPage.clickPromptBtn("Cancel");
        logger.debug("Checking if Prompt message is matching.");
        Assert.assertTrue(message.equalsIgnoreCase("I am a JS prompt"), "The message must display 'I am a JS prompt'");
        logger.debug("Checking if proper Prompt Result is shown.");
        Assert.assertTrue(jSAlertsPage.getResult().equalsIgnoreCase("You entered: null"), "The message must display 'You entered: null'");
    }
    
}
