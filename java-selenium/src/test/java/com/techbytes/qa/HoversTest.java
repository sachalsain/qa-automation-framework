package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Hovers Page")
    @Description("Testing Hovers Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void hoversTest() {
        logger.info(" -> Starting Test of Hovers Page");
        logger.debug(" -> Creating HoversPage instance.");
        HoversPage hoversPage = new HoversPage(getDriver());
        logger.debug(" -> Opening Hovers Page.");
        hoversPage.open();

        logger.debug(" -> Verifying if the proper user value is displayed correctly for 2nd user.");
        Assert.assertTrue(hoversPage.isProperLabelDisplayed(2), "The label for user2 should be visible.");
        logger.debug(" -> Verified that the proper user value is displayed correctly for 2nd user.");
    }

}
