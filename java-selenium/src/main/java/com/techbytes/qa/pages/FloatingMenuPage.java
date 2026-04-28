package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class FloatingMenuPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FloatingMenuPage.class);

    public FloatingMenuPage(WebDriver driver) {
        logger.info(" -> Setting up the Floating Menu Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Floating Menu Page...");
        openUrl(ConfigurationLoader.get("floatingMenuUrl"));
    }

    public void scrollDown() {
        logger.info(" -> Scrolling the Floating Menu Page down 1000 pixels.");
        new Actions(driver).scrollByAmount(0, 1000).perform();
    }

    public boolean isMenuVisible() {
        logger.info(" -> Checking if menu is visible");
        return driver.findElement(By.cssSelector("#menu")).isDisplayed();
    }
}
