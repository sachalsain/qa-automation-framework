package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.core.BrowserType;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.core.DriverFactory;
import com.techbytes.qa.listeners.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ABTestingTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ABTestingTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("A/B testing using different browsers")
    @Description("Verify A/B testing using different browsers")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void ABTest() {
        logger.info(" -> A/B Testing using Chrome Browser and Firefox");
        logger.debug(" -> Creating Chrome Driver.");
        WebDriver chromeDriver = DriverFactory.init(BrowserType.CHROME.name());
        WebDriver firefoxDriver = null;

        try {
            logger.debug(" -> Creating Firefox Driver.");
            firefoxDriver = new FirefoxDriver();

            logger.debug(" -> Opening ABTest page using Chrome.");
            chromeDriver.get(ConfigurationLoader.get("abtesturl"));
            logger.debug(" -> Opening ABTest page using Firefox.");
            firefoxDriver.get(ConfigurationLoader.get("abtesturl"));

            String chromeHeading = chromeDriver.findElement(By.tagName("h3")).getText();
            String firefoxHeading = firefoxDriver.findElement(By.tagName("h3")).getText();

            logger.debug(" -> Chrome heading: {}", chromeHeading);
            logger.debug(" -> Firefox heading: {}", firefoxHeading);

            logger.debug(" -> Verifying if the headings on both browsers are different.");
            Assert.assertNotEquals(firefoxHeading, chromeHeading, "A/B Testing headings should match in Chrome and Firefox.");
            logger.debug(" -> Verified that the headings on both browsers are different.");
        } finally {
            if (firefoxDriver != null) {
                firefoxDriver.quit();
            }
        }
    }
}
