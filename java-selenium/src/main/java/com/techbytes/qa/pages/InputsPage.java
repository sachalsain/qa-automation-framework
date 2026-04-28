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
public class InputsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(InputsPage.class);

    public InputsPage(WebDriver driver) {
        logger.info(" -> Setting up the Inputs Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Inputs Page...");
        openUrl(ConfigurationLoader.get("inputsUrl"));
    }
    
    public void fillNumber(int number) {
        logger.info(" -> Filling value in the input field.");
        driver.findElement(By.cssSelector("input[type='number']")).sendKeys(String.valueOf(number));
    }
    
    public int getValue() {
        logger.info(" -> Filling value in the input field.");
        return Integer.parseInt(driver.findElement(By.cssSelector("input[type='number']")).getAttribute("value"));
    }
    
}
