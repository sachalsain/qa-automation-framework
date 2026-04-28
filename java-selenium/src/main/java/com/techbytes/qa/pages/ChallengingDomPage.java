package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ChallengingDomPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ChallengingDomPage.class);

    public ChallengingDomPage(WebDriver driver) {
        logger.info(" -> Setting up the Challenging DOM Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the Challenging DOM Page...");
        openUrl(ConfigurationLoader.get("challengingDomUrl"));
    }
    
    public int getTableHeadersCount() {
        logger.info(" -> Getting and returning total numbers of headers.");
        List<WebElement> headers = driver.findElements(By.cssSelector("thead tr th"));
        return headers.size();
    }
    
    public int getTableDataRowCount() {
        logger.info(" -> Getting and returning total numbers of rows.");
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
        return rows.size();
    }
    
    public String getTableDataValue(int row, int column) {
        logger.info(" -> Getting and returning the value of data cell on row: {} and column: {}.", row, column);
        return driver.findElements(By.cssSelector("table tbody tr")).get(row).findElements(By.tagName("td")).get(column).getText();
    }
    
}
