package com.techbytes.qa.tests;

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
    @Story("Testing Challenging DOM")
    @Description("Testing Challenging DOM")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void DOMTest() {
        logger.info("Testing Table");
        logger.debug("Creating ChallengingDomPage instance.");
        ChallengingDomPage domPage = new ChallengingDomPage(page);
        logger.debug("Opening ChallengingDom Page.");
        domPage.open();
        logger.debug("Verifying number of headers.");
        Assert.assertEquals(domPage.getTableHeadersCount(), Integer.parseInt(ConfigurationLoader.get("tableHeaderCount")), "The nummber of headers must match.");
        logger.debug("Verifying number of data rows.");
        Assert.assertEquals(domPage.getTableDataRowCount(), Integer.parseInt(ConfigurationLoader.get("tableDataRowCount")), "The nummber of data rows must match.");
        logger.debug("Matching the text value of a data cell.");
        Assert.assertEquals(domPage.getTableDataValue(3, 3), "Definiebas3", "The value must match.");
    }
    
}
