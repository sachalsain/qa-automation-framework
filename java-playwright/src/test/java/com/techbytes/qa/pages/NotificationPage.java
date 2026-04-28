package com.techbytes.qa.pages;

import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class NotificationPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(NotificationPage.class);

    public NotificationPage(Page page) {
        logger.info("Setting up the Notification Page.");
        super(page);
    }

    private final String message = "#flash";
    private final String clickHereLink = "Click here";
    
    public void open() {
        logger.info("Navigating to the Notification Page URL.");
        page.navigate(ConfigurationLoader.get("notificationUrl"));
    }
    
    public void clickLink() {
        logger.info("Clicking the link to load success or error message.");
        page.getByText(clickHereLink).click();
    }
    
    public String getMessageText() {
        logger.info("Returning the notification message displayed.");
        return page.locator(message).textContent();
    }
}
