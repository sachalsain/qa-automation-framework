package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.utilities.WaitUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class JqueryuiPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(JqueryuiPage.class);

    public JqueryuiPage(WebDriver driver) {
        logger.info(" -> Setting up the Jquery UI Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Jquery UI Page...");
        openUrl(ConfigurationLoader.get("jqueryuiUrl"));
    }
    
    public boolean checkDisabled() {
        logger.info(" -> Checking if the menu Item is disabled.");
        String attrClass = driver.findElement(By.cssSelector("#ui-id-1")).getAttribute("class");
        return attrClass.contains("ui-state-disabled");
    }
    
    public boolean checkEnabled() {
        logger.info(" -> Checking if the menu Item is enabled.");
        String attrClass = driver.findElement(By.cssSelector("#ui-id-3")).getAttribute("class");
        return !attrClass.contains("ui-state-disabled");
    }
    
    public void hoverEnabled() {
        logger.info(" -> Hovering over Enabled Menu Item.");
        WebElement target = driver.findElement(By.xpath("//a[contains(text(), 'Enabled')]"));
        logger.debug(" -> Creating Action.");
        Actions action = new Actions(driver);
        logger.debug(" -> Performing the hover action.");
        action.moveToElement(target).perform();
        logger.debug(" -> Initializing the wait for the visibility state of the element to change.");
        WaitUtilities waitUtility = new WaitUtilities(driver);
        logger.debug(" -> Waiting for the visibility state of the element to change.");
        waitUtility.waitForVisible(By.xpath("//a[contains(text(), 'Downloads')]"));
    }
    
    public boolean isDownloadsVisible() {
        logger.info(" -> Returning visibility of downloads menu item");
        return driver.findElement(By.xpath("//a[contains(text(), 'Downloads')]")).isDisplayed();
    }
    
    public void hoverDownloads() {
        logger.info(" -> Hovering over Downloads Menu Item.");
        WebElement target = driver.findElement(By.xpath("//a[contains(text(), 'Downloads')]"));
        logger.debug(" -> Creating Action.");
        Actions action = new Actions(driver);
        logger.debug(" -> Performing the hover action.");
        action.moveToElement(target).perform();
        logger.debug(" -> Initializing the wait for the visibility state of the element to change.");
        WaitUtilities waitUtility = new WaitUtilities(driver);
        logger.debug(" -> Waiting for the visibility state of the element to change.");
        waitUtility.waitForVisible(By.xpath("//a[contains(text(), 'CSV')]"));
    }
    
    public boolean isCSVVisible() {
        logger.info(" -> Returning visibility of CSV menu item");
        return driver.findElement(By.xpath("//a[contains(text(), 'CSV')]")).isDisplayed();
    }
    
}
