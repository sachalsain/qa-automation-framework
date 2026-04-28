package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.utilities.WaitUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ShiftingContentMenuPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ShiftingContentMenuPage.class);

    public ShiftingContentMenuPage(WebDriver driver) {
        logger.info(" -> Setting up the Shifting Content Menu Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Shifting Content Menu Page...");
        openUrl(ConfigurationLoader.get("shiftingContentMenuUrl"));
    }

    public String getUrl(int val, String url) {
        logger.info(" -> Clicking the required link");
        driver.findElements(By.linkText("click here")).get(val).click();
//        logger.debug("Initializing the wait for the state of the url to change.");
//        WaitUtilities waitUtility = new WaitUtilities(driver);
//        logger.debug("Waiting for the url to change.");
//        waitUtility.waitForUrl(url);
        logger.debug(" -> Returning the page url");
        return driver.getCurrentUrl();
    }

}
