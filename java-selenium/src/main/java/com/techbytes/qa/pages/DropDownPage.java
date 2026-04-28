package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DropDownPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DropDownPage.class);

    public DropDownPage(WebDriver driver) {
        logger.info(" -> Setting up the Drop Down Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the Drop Down Page...");
        openUrl(ConfigurationLoader.get("dropDownUrl"));
    }
    
    public void selectValue(int value) {
        logger.info(" -> Setting value for dropdown element to {}.", value);
        logger.debug(" -> Creating a Select object.");
        Select select = new Select(driver.findElement(By.cssSelector("#dropdown")));
        logger.debug(" -> Selecting the Option to provided value.");
        select.selectByValue(String.valueOf(value));
    }
    
    public String getSelectedValue() {
        logger.info(" -> Returning the selected value of the dropdown element.");
        logger.debug("Creating a Select object.");
        Select select = new Select(driver.findElement(By.cssSelector("#dropdown")));
        logger.debug(" -> Getting the selected option element and then returning its text.");
        return select.getFirstSelectedOption().getText();
    }
}
