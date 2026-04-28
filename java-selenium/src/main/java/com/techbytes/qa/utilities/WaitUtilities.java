package com.techbytes.qa.utilities;

import com.techbytes.qa.core.ConfigurationLoader;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class WaitUtilities {

    private static final Logger logger = LoggerFactory.getLogger(WaitUtilities.class);

    private final WebDriverWait wait;

    public WaitUtilities(WebDriver driver) {
        logger.info(" -> Getting default explicit wait time.");
        int timeout = Integer.parseInt(ConfigurationLoader.get("explicitWait"));
        logger.debug(" -> Setting up the wait till explicit time.");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement waitForVisible(By locator) {
        logger.info(" -> Setting up the wait till visible.");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForListVisible(By locator) {
        logger.info(" -> Setting up the wait till all are visible.");
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForClickable(By locator) {
        logger.info(" -> Setting up the wait till clickable.");
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForText(By locator, String text) {
        logger.info(" -> Setting up the wait till text is available.");
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void waitForFunction(String jscript, int initialCount) {
        logger.info(" -> Setting up the wait till visible.");
        wait.until(driver -> {
            // Execute JS to get the current count
            long currentCount = (long) ((JavascriptExecutor) driver)
                    .executeScript(jscript);

            // Return true when the condition is met
            return currentCount > initialCount;
        });
    }

    public boolean waitForPopup() {
        logger.info(" -> Setting up the wait till new window is available.");
        return wait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public boolean waitForUrl(String urlPart) {
        logger.info(" -> Setting up the wait till url is updated.");
        return wait.until(ExpectedConditions.urlContains(urlPart));
    }
}
