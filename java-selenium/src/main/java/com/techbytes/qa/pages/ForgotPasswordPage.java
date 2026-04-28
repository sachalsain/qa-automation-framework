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
public class ForgotPasswordPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordPage.class);

    public ForgotPasswordPage(WebDriver driver) {
        logger.info(" -> Setting up the Forgot Password Page...");
        super(driver);
    }

    public void open() {
        logger.info("cOpening the Forgot Password Page...");
        openUrl(ConfigurationLoader.get("forgotPasswordUrl"));
    }

    public void submitForm(String email) {
        logger.info(" -> Entering the provided email and submitting form.");
        logger.debug(" -> Entering the email.");
        driver.findElement(By.cssSelector("#email")).sendKeys(email);
        logger.debug(" -> Clicking the submit button.");
        driver.findElement(By.cssSelector("#form_submit[type='submit']")).click();
    }

    public String getMessage() {
        logger.info(" -> Retrieving message.");
        return driver.findElement(By.cssSelector("h1")).getText();
    }
    
}
