package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class JSAlertsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(JSAlertsPage.class);

    public JSAlertsPage(WebDriver driver) {
        logger.info(" -> Setting up the JavaScript Alerts Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the JavaScript Alerts Page...");
        openUrl(ConfigurationLoader.get("jsAlertsUrl"));
    }

    public String clickAlertBtn() {
        logger.info(" -> Clicking the Alert button.");
        driver.findElement(By.xpath("//button[contains(text(), 'Click for JS Alert')]")).click();
        logger.info(" -> Creating Alert object for alert.");
        Alert alert = driver.switchTo().alert();
        logger.info(" -> Extracting message from alert.");
        String message = alert.getText();
        alert.accept(); // Clicks OK
        return message;
    }

    public String clickConfirmBtn(String value) {
        logger.info(" -> Clicking the Confirm button.");
        driver.findElement(By.xpath("//button[contains(text(), 'Click for JS Confirm')]")).click();
        logger.info(" -> Creating Alert object for Confirm alert.");
        Alert alert = driver.switchTo().alert();
        logger.info(" -> Extracting message from Confirm alert.");
        String message = alert.getText();
        if (value.equalsIgnoreCase("ok")) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return message;
    }

    public String clickPromptBtn(String value) {
        logger.info(" -> Clicking the Prompt button.");
        driver.findElement(By.xpath("//button[contains(text(), 'Click for JS Prompt')]")).click();
        logger.info(" -> Creating Alert object for Prompt alert.");
        Alert alert = driver.switchTo().alert();
        logger.info(" -> Extracting message from Prompt alert.");
        String message = alert.getText();
        if (value.equalsIgnoreCase("ok")) {
            alert.sendKeys(ConfigurationLoader.get("promptText"));
            alert.accept();
        } else {
            alert.dismiss();
        }
        return message;
    }

    public String getResult() {
        logger.info(" -> Returning the Result.");
        return driver.findElement(By.cssSelector("#result")).getText();
    }

}
