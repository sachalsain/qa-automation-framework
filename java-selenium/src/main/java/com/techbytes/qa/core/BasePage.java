package com.techbytes.qa.core;

import com.techbytes.qa.utilities.WaitUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public abstract class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    
    protected WebDriver driver;
    protected WaitUtilities wait;

    public BasePage(WebDriver driver) {
        logger.info(" -> Setting up the Base Page with driver and waitutil...");
        this.driver = driver;
        this.wait = new WaitUtilities(driver);
    }

    public void ChromePage(WebDriver driver) {
        logger.info(" -> Setting up the Base Page with driver and waitutil...");
        this.driver = new ChromeDriver();
        this.wait = new WaitUtilities(driver);
    }

    public void openUrl(String url) {
        logger.info("Navigating to requested URL: {}.", url);
        driver.get(url);
    }
    
    protected void click(By locator) {
        logger.info("Clikcking the element: {}.", locator);
        WebElement element = wait.waitForClickable(locator);
        element.click();
    }
    
    protected void type(By locator, String text) {
        logger.info("Entering {} in element: {}.", text, locator);
        WebElement element = wait.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    
    protected String getText(By locator) {
        logger.info("Returning text from element: {}.", locator);
        return wait.waitForVisible(locator).getText();
    }
    
    protected boolean isVisible(By locator) {
        logger.info("Returning if element: {} is visible.", locator);
        try {
            return wait.waitForVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
