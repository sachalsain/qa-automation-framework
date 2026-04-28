package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DynamicElementPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DynamicElementPage.class);

    public DynamicElementPage(WebDriver driver) {
        logger.info(" -> Setting up the Dynamic Element Page...");
        super(driver);
    }

    public void open(int pagenum) {
        logger.info(" -> Opening the Dynamic Element Page...");
        openUrl(ConfigurationLoader.get("dynamicElementUrl") + pagenum);
    }
    
    public void clickStartBtn() {
        logger.info(" -> Clicking the start button.");
        driver.findElement(By.cssSelector("#start button")).click();
        logger.debug(" -> Initializing the wait for the state of the loading element of Start button to change.");
        WebDriverWait tbWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logger.debug(" -> Waiting for the state of the loading element of Start button to change.");
        tbWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#loading")));
    }
    
    public boolean isStrtBtnDisplayed() {
        return driver.findElement(By.cssSelector("#start button")).isDisplayed();
    }
    
    public boolean isHelloDisplayed() {
        logger.info(" -> Checking Hello message visibility.");
        return driver.findElement(By.cssSelector("#finish h4")).isDisplayed();
    }
    
    public boolean isStrtBtnAvailable() {
        logger.info(" -> Checking start button visibility.");
        try {
            driver.findElement(By.cssSelector("#start button")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    
    public boolean isHelloAvailable() {
        logger.info(" -> Checking Hello message visibility.");
        try {
            driver.findElement(By.cssSelector("#finish h4")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    
}
