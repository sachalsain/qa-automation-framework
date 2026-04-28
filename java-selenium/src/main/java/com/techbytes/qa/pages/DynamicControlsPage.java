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
public class DynamicControlsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DynamicControlsPage.class);

    public DynamicControlsPage(WebDriver driver) {
        logger.info(" -> Setting up the Dynamic Controls Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Dynamic Controls Page...");
        openUrl(ConfigurationLoader.get("dynamicControlsUrl"));
    }

    public void reloadPage() {
        logger.info(" -> Reloading the Dynamic Content Page URL.");
        driver.get(driver.getCurrentUrl());
    }

    public boolean isCheckboxDisplayed() {
        logger.info(" -> Checking checkbox element visibility.");
        try {
            driver.findElement(By.cssSelector("#checkbox-example #checkbox")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void clickRemoveBtn() {
        logger.info(" -> Clicking the remove button.");
        driver.findElement(By.cssSelector("#checkbox-example button[type='button']")).click();
        logger.debug(" -> Initializing the wait for the state of the loading element of checkbox to change.");
        WebDriverWait tbWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logger.debug(" -> Waiting for the state of the loading element of checkbox to change.");
        tbWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#checkbox-example #loading")));
    }

    public boolean isChkBoxSuccessDisplayed() {
        logger.info(" -> Checking success message on checkbox removal.");
        try {
            driver.findElement(By.cssSelector("#checkbox-example #message")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean isTextBoxEnabled() {
        logger.info(" -> Checking if textkbox is enabled.");
        return driver.findElement(By.cssSelector("#input-example input[type='text']")).isEnabled();
    }

    public void clickEnableBtn() {
        logger.info(" -> Clicking the Disable button.");
        driver.findElement(By.cssSelector("#input-example button[type='button']")).click();
        logger.debug(" -> Initializing the wait for the state of the loading element of textbox to change.");
        WebDriverWait tbWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logger.debug(" -> Waiting for the state of the loading element of textbox to change.");
        tbWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#input-example #loading")));
    }

    public boolean isTextBoxSuccessDisplayed() {
        logger.info(" -> Checking success message visibility.");
        try {
            driver.findElement(By.cssSelector("#input-example #message")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
