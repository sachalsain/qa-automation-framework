package com.techbytes.qa.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class AddDeletePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AddDeletePage.class);

    public AddDeletePage(WebDriver driver) {
        logger.info(" -> Setting up the Add / Delete Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Add / Delete Page...");
        openUrl(ConfigurationLoader.get("addDeleteurl"));
    }

    public boolean isAddButtonVisible() {
        logger.info(" -> Checking add button visibility.");
        try {
            driver.findElement(By.cssSelector("button[onclick='addElement()']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public boolean isDeleteButtonVisible() {
        logger.info(" -> Checking delete button visibility.");
        logger.debug(" -> Verifying if delete button is diplayed.");
        try {
            driver.findElement(By.cssSelector("button[onclick='deleteElement()']")).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.debug(" -> Delete button not found.");
            return false;
        }
        logger.debug(" -> Verifried that delete button is diplayed. Returning TRUE.");
        return true;
    }

    public void clickAddButton() {
        logger.info(" -> Clicking Add button.");
        driver.findElement(By.cssSelector("button[onclick='addElement()']")).click();
    }

    public void clickDeleteButton() {
        logger.info(" -> Clicking Delete button.");
        driver.findElement(By.cssSelector("button[onclick='deleteElement()']")).click();
    }

}
