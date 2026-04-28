package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.utilities.WaitUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class InfiniteScrollPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(InfiniteScrollPage.class);

    public InfiniteScrollPage(WebDriver driver) {
        logger.info(" -> Setting up the Infinite Scroll Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Infinite Scroll Page...");
        openUrl(ConfigurationLoader.get("infiniteScrollUrl"));
    }
    
    public int getParaCount() {
        logger.info(" -> Returning the count of paras.");
        return driver.findElements(By.cssSelector(".jscroll-added")).size();
        
    }

    public void scrollDown() {
        logger.info(" -> Scrolling down the page.");
        // Execute JS to scroll to bottom
        logger.debug(" -> Casting the driver to JavascriptExecutor.");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        logger.debug(" -> Executing the Javascript.");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Wait for new content to be added
        logger.debug(" -> waiting for the para to load.");
        logger.debug(" -> Initializing the wait for the state of the function to change.");
        WaitUtilities waitUtility = new WaitUtilities(driver);
        logger.debug(" -> Waiting for the state of the function to change.");
        waitUtility.waitForFunction("return document.querySelectorAll('.jscroll-added').length;", getParaCount());
    }
    
}
