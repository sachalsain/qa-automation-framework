package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class CheckBoxPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckBoxPage.class);

    public CheckBoxPage(WebDriver driver) {
        logger.info(" -> Setting up the CheckBox Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the CheckBox Page...");
        openUrl(ConfigurationLoader.get("checkboxurl"));
    }
    
    public int getCheckboxCount() {
        logger.info(" -> Getting and returning total number of checkboxes in the container.");
        return driver.findElements(By.cssSelector("#checkboxes input[type='checkbox']")).size();
    }
    
    public WebElement getCheckbox(int number) {
        logger.info(" -> Returning checkbox element at index {}.", number);
        return driver.findElements(By.cssSelector("#checkboxes input[type='checkbox']")).get(number);
    }
    
    public void selectCheckbox(int number) {
        logger.info(" -> Selecting checkbox element at index {}.", number);
        driver.findElements(By.cssSelector("#checkboxes input[type='checkbox']")).get(number).click();
    }
}
