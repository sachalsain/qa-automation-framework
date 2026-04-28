package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.ShiftingContentMenuPage;
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
public class ShiftingContentMenuTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ShiftingContentMenuTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Shifting Content Menu Test.")
    @Description("Test to check Shifting Content Menu Page work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void ShiftingContentMenuTest() {
        logger.info("Testing Shifting Content Menu");
        logger.debug("Creating ShiftingContentMenuPage instance.");
        ShiftingContentMenuPage scmenuPage = new ShiftingContentMenuPage(page);
        logger.debug("Opening Shadow DOM Page.");
        scmenuPage.open();
        logger.debug("clicking the Mode link.");
        String urlClicked = scmenuPage.getUrl(1);
        Assert.assertTrue(urlClicked.contains("mode"), "The url must contain 'mode'.");
        logger.debug("clicking the Pixel Shift link.");
        urlClicked = scmenuPage.getUrl(2);
        Assert.assertTrue(urlClicked.contains("pixel_shift"), "The url must contain 'pixel_shift'.");
        logger.debug("clicking the Mode and Pixel Shift link.");
        urlClicked = scmenuPage.getUrl(3);
        Assert.assertTrue(urlClicked.contains("mode") && urlClicked.contains("pixel_shift"), "The url must contain both 'mode' and 'pixel_shift'.");
    }
}
