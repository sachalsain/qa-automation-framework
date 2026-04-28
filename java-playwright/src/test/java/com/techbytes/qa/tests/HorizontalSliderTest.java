package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.HorizontalSliderPage;
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
public class HorizontalSliderTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(HorizontalSliderTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Horizontal Slider Page Test.")
    @Description("Test to check Horizontal Slider work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void horizontalSliderTest() {
        logger.info("Testing Horizontal Slider");
        logger.debug("Creating HorizontalSliderPage instance.");
        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage(page);
        logger.debug("Opening Horizontal Slider Page.");
        horizontalSliderPage.open();
        logger.debug("Dragging slider to specified value.");
        horizontalSliderPage.setSliderValue(4);
        logger.debug("Checking if the value is displayed correctly.");
        Assert.assertEquals(horizontalSliderPage.getCurrentValue(), 4, "The value 4 should be shown.");
    }
    
}
