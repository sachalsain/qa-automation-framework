package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ContextMenuPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ContextMenuPage.class);

    public ContextMenuPage(WebDriver driver) {
        logger.info(" -> Setting up the Context Menu Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Context Menu Page...");
        openUrl(ConfigurationLoader.get("contextMenuUrl"));
    }

    public String getTextOfAlert() {
        logger.info(" -> Returning text shown on alert.");
        logger.debug(" -> Creating alert listener to capture the message.");
        String message;
        // Selenium waits for browser alert after action
        logger.debug(" -> Registering Action for right click.");
        Actions actions = new Actions(driver);
        logger.debug(" -> Right clicking to activate alert.");
        actions.contextClick(driver.findElement(By.id("hot-spot"))).perform();

        logger.debug(" -> Registering Alert to capture message.");
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        logger.debug(" -> Retrieving text.");
        message = alert.getText();
        logger.debug(" -> Dismissing the alert box.");
        alert.accept();
        logger.debug(" -> Returning captured Message.");
        return message;
    }
}
