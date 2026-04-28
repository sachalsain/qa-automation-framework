package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.HoversPage;
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
public class HoversTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(HoversTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Hover Page Test.")
    @Description("Test to check Hover work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void hoverTest() {
        logger.info("Testing Hover");
        logger.debug("Creating HoversPage instance.");
        HoversPage hoversPage = new HoversPage(page);
        logger.debug("Opening Hover Page.");
        hoversPage.open();
        logger.debug("Checking if the proper user value is displayed correctly for 2nd user.");
        Assert.assertTrue(hoversPage.isProperLabelDisplayed(2), "The label for user2 should be visible.");
    }
    
}
