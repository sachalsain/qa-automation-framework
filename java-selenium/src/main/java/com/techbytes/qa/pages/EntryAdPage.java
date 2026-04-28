package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.utilities.WaitUtilities;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class EntryAdPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(EntryAdPage.class);

    public EntryAdPage(WebDriver driver) {
        logger.info(" -> Setting up the Entry Ad Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Entry Ad Page...");
        openUrl(ConfigurationLoader.get("entryAdUrl"));
    }
    
    public boolean isWindowVisible() {
        logger.info(" -> Waiting for ad window to be displayed.");
        logger.debug(" -> Initializing the wait for the state of the element of window to change.");
        WaitUtilities waitUtility = new WaitUtilities(driver);
//        WebDriverWait tbWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logger.debug(" -> Waiting for the state of the loading element of window to change.");
        waitUtility.waitForVisible(By.cssSelector("#modal"));
//        tbWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#modal")));
        logger.debug(" -> Returning visibility status.");
        return driver.findElement(By.cssSelector("#modal")).isDisplayed();
    }
    
    public boolean isWindowHidden() {
        logger.info(" -> Returning visibility status.");
        return driver.findElement(By.cssSelector("#modal")).isDisplayed();
    }
    
    public String getWindowTitle() {
        logger.info(" -> Returning Title.");
        return driver.findElement(By.cssSelector("#modal .modal .modal-title h3")).getText();
    }
    
    public String getWindowText() {
        logger.info(" -> Returning Body text.");
        return driver.findElement(By.cssSelector("#modal .modal .modal-body p")).getText();
    }
    
    public String getWindowClose() {
        logger.info(" -> Returning Footer text.");
        return driver.findElement(By.cssSelector("#modal .modal .modal-footer p")).getText();
    }
    
    public void closeWindow() {
        logger.info(" -> Clicking the close button.");
        driver.findElement(By.cssSelector("#modal .modal .modal-footer p")).click();
    }

    public void reloadPage() {
        logger.info(" -> Reloading page.");
        driver.get(driver.getCurrentUrl());
    }
    
}
