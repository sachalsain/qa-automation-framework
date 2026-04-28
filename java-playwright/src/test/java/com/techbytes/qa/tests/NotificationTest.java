package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.NotificationPage;
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
public class NotificationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(NotificationTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Notification Test.")
    @Description("Test to check Notification Page work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void KeyPressTest() {
        logger.info("Testing Notification");
        logger.debug("Creating NotificationPage instance.");
        NotificationPage notificationPage = new NotificationPage(page);
        logger.debug("Opening Notification Page.");
        notificationPage.open();
        logger.debug("Cliking the link.");
        notificationPage.clickLink();
        logger.debug("Checking the notification message is as per reqirement.");
        Assert.assertTrue(notificationPage.getMessageText().contains("Action successful") || notificationPage.getMessageText().contains("Action unsuccesful"), "The notification message must contain 'Action successful' OR 'Action unsuccesful'.");
    }
}
