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
public class DisappearingElementsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DisappearingElementsPage.class);

    public DisappearingElementsPage(WebDriver driver) {
        logger.info(" -> Setting up the Disappearing Elements Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the Disappearing Elements Page...");
        openUrl(ConfigurationLoader.get("disappearingElementsUrl"));
    }
    
    public boolean isMenuVisible() {
        logger.info(" -> Checking menu element visibility.");
        return driver.findElement(By.cssSelector("#content")).isDisplayed();
    }
    
    public int countMenuItems() {
        logger.info(" -> Getting and returning total number of menu items in the container.");
        return driver.findElements(By.cssSelector("#content ul li")).size();
    }
}
