package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.ChallengingDomPage;
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
public class ChallengingDomTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ChallengingDomTest.class);
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Challenging DOM Page")
    @Description("Testing Challenging DOM Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void challengingDomTest() {
        logger.info(" -> Starting Test of Challenging DOM Page");
        logger.debug(" -> Creating ChallengingDomPage instance.");
        ChallengingDomPage challengingDomPage = new ChallengingDomPage(getDriver());
        logger.debug(" -> Opening ChallengingDom Page.");
        challengingDomPage.open();
        logger.debug(" -> Verifying number of headers.");
        Assert.assertEquals(challengingDomPage.getTableHeadersCount(), Integer.parseInt(ConfigurationLoader.get("tableHeaderCount")), "The nummber of headers must match.");
        logger.debug(" -> Verified number of headers.");
        
        logger.debug(" -> Verifying number of data rows.");
        Assert.assertEquals(challengingDomPage.getTableDataRowCount(), Integer.parseInt(ConfigurationLoader.get("tableDataRowCount")), "The nummber of data rows must match.");
        logger.debug(" -> Verified number of data rows.");
        
        logger.debug(" -> Verifying if the text value of a data cell matches with expected.");
        Assert.assertEquals(challengingDomPage.getTableDataValue(3, 3), "Definiebas3", "The value must match.");
        logger.debug(" -> Verified that the text value of a data cell matches with expected.");
    }
    
}
