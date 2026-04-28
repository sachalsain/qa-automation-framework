package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class HoversPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HoversPage.class);

    public HoversPage(WebDriver driver) {
        logger.info(" -> Setting up the Hovers Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Hovers Page...");
        openUrl(ConfigurationLoader.get("hoversUrl"));
    }
    
    public void hoverOn(int num) {
        logger.info(" -> Hovering on element at index: {}.", num - 1);
        WebElement target = driver.findElements(By.cssSelector("img[alt='User Avatar']")).get(num - 1);
        logger.debug(" -> Creating Action.");
        Actions action = new Actions(driver);
        logger.debug(" -> Performing the move action.");
        action.moveToElement(target).perform();
    }
    
    public boolean isProperLabelDisplayed(int num) {
        logger.info(" -> Checking if proper Label is displayed.");
        logger.debug(" -> Requesting hover.");
        hoverOn(num);
        logger.debug(" -> Fetching all locators.");
        List<WebElement> locators = driver.findElements(By.cssSelector(".figcaption h5"));
        logger.debug(" -> Returning if proper Label is displayed.");
        return locators.get(num-1).isDisplayed();
    }
    
}
