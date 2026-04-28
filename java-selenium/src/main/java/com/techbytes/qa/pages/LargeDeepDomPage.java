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
public class LargeDeepDomPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LargeDeepDomPage.class);

    public LargeDeepDomPage(WebDriver driver) {
        logger.info(" -> Setting up the Large Deep DOM Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Large Deep DOM Page...");
        openUrl(ConfigurationLoader.get("largeDeepDomUrl"));
    }
    
    public boolean isTableVisible() {
        logger.info(" -> Returning visibility of the table.");
        return driver.findElement(By.cssSelector("#large-table")).isDisplayed();
    }
    
    public String getValueOfCell(int row, int cell) {
        logger.info(" -> Returning the value of required cell.");
        return driver.findElement(By.cssSelector(".row-" + row + " .column-" + cell)).getText();
    }
    
}
