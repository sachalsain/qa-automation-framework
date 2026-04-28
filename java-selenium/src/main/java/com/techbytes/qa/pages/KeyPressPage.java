package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class KeyPressPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(KeyPressPage.class);

    public KeyPressPage(WebDriver driver) {
        logger.info(" -> Setting up the Key Press Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Key Press Page...");
        openUrl(ConfigurationLoader.get("keyPressesUrl"));
    }
    
    public void clickInput() {
        logger.info(" -> Clicking the input field.");
        driver.findElement(By.cssSelector("#target")).click();
    }
    
    public String getResult() {
        logger.info(" -> Returning the result displayed.");
        return driver.findElement(By.cssSelector("#result")).getText();
    }
    
    public void pressKey(Keys key) {
        logger.info(" -> Pressing the required key.");
        driver.findElement(By.cssSelector("#target")).sendKeys(key);
    }
    
}
