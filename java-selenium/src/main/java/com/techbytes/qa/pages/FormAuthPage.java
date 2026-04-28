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
public class FormAuthPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FormAuthPage.class);

    public FormAuthPage(WebDriver driver) {
        logger.info(" -> Setting up the Form Authentication Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Form Authentication Page...");
        openUrl(ConfigurationLoader.get("loginurl"));
    }

    public void login(String username, String password) {
        logger.info(" -> Login requested.");
        logger.debug(" -> Entering username.");
        driver.findElement(By.cssSelector("#username")).sendKeys(username);
        logger.debug(" -> Entering password.");
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        logger.debug(" -> Clicking login button.");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    public String getMessage() {
        logger.info(" -> Returning Message.");
        return driver.findElement(By.cssSelector("#flash")).getText();
    }

    public boolean isErrorVisible() {
        logger.info(" -> Locator visibility requested.");
        logger.debug(" -> Retrning if the locator is visible.");
        return driver.findElement(By.cssSelector("#flash")).isDisplayed();
    }
    
}
