package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Notification Page")
    @Description("Testing Notification Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void notificationTest() {
        logger.info(" -> Starting Test of Notification Page");
        logger.debug(" -> Creating NotificationPage instance.");
        NotificationPage page = new NotificationPage(getDriver());
        logger.debug(" -> Opening Notification Page.");
        page.open();
        
        logger.debug(" -> Cliking the link.");
        page.clickLink();
        
        logger.debug(" -> Verifying if the notification message is as per reqirement.");
        Assert.assertTrue(page.getMessageText().contains("Action successful") || page.getMessageText().contains("Action unsuccesful"), "The notification message must contain 'Action successful' OR 'Action unsuccesful'.");
        logger.debug(" -> Verified that the notification message is as per reqirement.");
    }
    
}
