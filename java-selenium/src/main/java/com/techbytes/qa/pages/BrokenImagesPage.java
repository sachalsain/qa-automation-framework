package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class BrokenImagesPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BrokenImagesPage.class);

    public BrokenImagesPage(WebDriver driver) {
        logger.info(" -> Setting up the Broken Images Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the Broken Images Page...");
        openUrl(ConfigurationLoader.get("brokenImagesUrl"));
    }

    public boolean isPageloaded() {
        logger.info(" -> Checking the container element visibility.");
        try {
            driver.findElement(By.cssSelector(".example img")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public List<WebElement> getAllImages() {
        logger.info(" -> Returning all images contained in the locator.");
        return driver.findElements(By.cssSelector(".example img"));
    }
    
}
