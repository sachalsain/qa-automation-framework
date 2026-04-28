package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Large Deep DOM Page")
    @Description("Testing Large Deep DOM Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void largeDeepDomTest() {
        logger.info(" -> Starting Test of Large Deep DOM Page");
        logger.debug(" -> Creating LargeDeepDomPage instance.");
        LargeDeepDomPage domPage = new LargeDeepDomPage(getDriver());
        logger.debug(" -> Opening Large Deep DOM Page.");
        domPage.open();
        
        logger.debug(" -> Verifying if the Table is visible.");
        Assert.assertTrue(domPage.isTableVisible(), "The Table must be visible.");
        logger.debug(" -> Verified that the Table is visible.");
        
        logger.debug(" -> Verifying if the value of the cell on row 10 and column 5 matches.");
        Assert.assertEquals(domPage.getValueOfCell(10, 5), "10.5", "The cell value must be 10.5");
        logger.debug(" -> Verified that the value of the cell on row 10 and column 5 matches.");
    }
    
}
