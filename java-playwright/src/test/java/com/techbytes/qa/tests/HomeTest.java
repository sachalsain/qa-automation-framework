package com.techbytes.qa.tests;

import com.microsoft.playwright.Locator;
import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class HomeTest extends BaseTest {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Links are displayed.")
    @Description("Check for Visibility of the all links.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void homeTest() {
        logger.info("Testing visiblity of all links");
        logger.debug("Creating HomePage instance.");
        HomePage homePage = new HomePage(page);
        logger.debug("Opening HomePage.");
        homePage.open(ConfigurationLoader.get("baseurl"));
        logger.debug("Getting all the links on the page.");
        int linkCount = homePage.getAllLinks().size();
        logger.debug("Getting favicon.");
        Locator favicon = homePage.getFavicon();
        logger.debug("Verify if 44 links are listed on the page.");
        Assert.assertEquals(linkCount, 44, "Must exactly be 44 links");
        logger.debug("Verified that 44 links are listed on the page.");
        logger.debug("Verify if favicon is displayed.");
        Assert.assertTrue((favicon.isVisible() & favicon.count() > 0), "The favicon must be available");
        logger.debug("Verified that favicon is displayed.");
    }
}
