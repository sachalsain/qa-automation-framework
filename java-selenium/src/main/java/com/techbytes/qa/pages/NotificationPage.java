package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class NotificationPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(NotificationPage.class);

    public NotificationPage(WebDriver driver) {
        logger.info(" -> Setting up the Notification Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Notification Page...");
        openUrl(ConfigurationLoader.get("notificationUrl"));
    }
    
    public void clickLink() {
        logger.info(" -> Clicking the link to load success or error message.");
        driver.findElement(By.linkText("Click here")).click();
    }
    
    public String getMessageText() {
        logger.info(" -> Returning the notification message displayed.");
        return driver.findElement(By.cssSelector("#flash")).getText();
    }
    
}
