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
public class NewWindowPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(NewWindowPage.class);
    
    private String originalWindow;

    public NewWindowPage(WebDriver driver) {
        logger.info(" -> Setting up the New Window Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the New Window Page...");
        openUrl(ConfigurationLoader.get("newWindowUrl"));
    }

    public void newPage() {
        logger.info(" -> Getting the handle of the current (original) window.");
        originalWindow = driver.getWindowHandle();
        logger.info(" -> Clicking the new page link.");
        driver.findElement(By.linkText("Click Here")).click();
        logger.debug(" -> Initializing the wait for the state of the function to change.");
        WaitUtilities waitUtility = new WaitUtilities(driver);
        logger.debug(" -> Waiting for the window to change.");
        waitUtility.waitForPopup();
    }

    public String getNewPageText() {
        logger.info(" -> Returning the text displayed on new page.");
        logger.debug(" -> Accessing the new window.");
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        logger.debug(" -> Returning the Text.");
        return driver.findElement(By.cssSelector("h3")).getText();
    }

}
