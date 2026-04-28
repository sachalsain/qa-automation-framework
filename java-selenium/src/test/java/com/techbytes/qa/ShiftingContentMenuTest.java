package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.ShadowDomPage;
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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Shifting Content Menu Page")
    @Description("Testing Shifting Content Menu Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void shiftingContentMenuTest() {
        logger.info(" -> Starting Test of Shifting Content Menu Page");
        logger.debug(" -> Creating ShiftingContentMenuPage instance.");
        ShiftingContentMenuPage page = new ShiftingContentMenuPage(getDriver());
        logger.debug(" -> Opening Shifting Content Menu Page.");
        page.open();
        
        logger.debug(" -> Clicking the Mode link.");
        String urlClicked = page.getUrl(0, "mode");
        
        logger.debug(" -> Verifying if the url contains 'mode'.");
        Assert.assertTrue(urlClicked.contains("mode"), "The url: '" + urlClicked + "' must contain 'mode'.");
        logger.debug(" -> Verified that the url contains 'mode'.");
        
        logger.debug(" -> Clicking the Pixel Shift link.");
        urlClicked = page.getUrl(1, "pixel_shift");
        
        logger.debug(" -> Verifying if the url contains 'pixel_shift'.");
        Assert.assertTrue(urlClicked.contains("pixel_shift"), "The url must contain 'pixel_shift'.");
        logger.debug(" -> Verified that the url contains 'pixel_shift'.");
        
        logger.debug(" -> Clicking the Mode and Pixel Shift link.");
        urlClicked = page.getUrl(2, "pixel_shift");
        
        logger.debug(" -> Verifying if the url contains both 'mode' and 'pixel_shift'.");
        Assert.assertTrue(urlClicked.contains("mode") && urlClicked.contains("pixel_shift"), "The url must contain both 'mode' and 'pixel_shift'.");
        logger.debug(" -> Verified that the url contains both 'mode' and 'pixel_shift'.");
    }
    
}
