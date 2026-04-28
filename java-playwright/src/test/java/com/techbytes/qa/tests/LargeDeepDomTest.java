package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.LargeDeepDomPage;
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
public class LargeDeepDomTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LargeDeepDomTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Large Deep Dom Test.")
    @Description("Test to check Large Deep Dom work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void KeyPressTest() {
        logger.info("Testing Large Deep Dom");
        logger.debug("Creating LargeDeepDomPage instance.");
        LargeDeepDomPage largeDeepDomPage = new LargeDeepDomPage(page);
        logger.debug("Opening Large Deep Dom Page.");
        largeDeepDomPage.open();
        logger.debug("Checking if the Table is visible.");
        Assert.assertTrue(largeDeepDomPage.isTableVisible(), "The Table must be visible.");
        logger.debug("Checking if the value of the cell on row 10 and column 5 matches.");
        Assert.assertEquals(largeDeepDomPage.getValueOfCell(10, 5), "10.5", "The cell value must be 10.5");        
    }
}
