package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.JqueryuiPage;
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
public class JqueryuiTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(JqueryuiTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Jqueryui Test.")
    @Description("Test to check Jqueryui work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void jqueryuiTest() {
        logger.info("Testing Jqueryui");
        logger.debug("Creating JqueryuiPage instance.");
        JqueryuiPage jqueryuiPage = new JqueryuiPage(page);
        logger.debug("Opening Jqueryui Page.");
        jqueryuiPage.open();
        logger.debug("Checking if the first menu item is disabled.");
        Assert.assertTrue(jqueryuiPage.checkDisabled(), "The first menu item must be diabled by default.");
        logger.debug("Checking if the Second menu item is enabled.");
        Assert.assertTrue(jqueryuiPage.checkEnabled(), "The Second menu item must be enabled by default.");
        logger.debug("Hover over Enabled menu item to show subMenu.");
        jqueryuiPage.hoverEnabled();
        logger.debug("Check if subMenu with item Downloads is visible.");
        Assert.assertTrue(jqueryuiPage.isDownloadsVisible(), "The Downloads menu item must be visible on mouse over.");
        logger.debug("Hover over Downloads menu item to show subMenu.");
        jqueryuiPage.hoverDownloads();
        logger.debug("Check if subMenu with item CSV is visible.");
        Assert.assertTrue(jqueryuiPage.isCSVVisible(), "The CSV menu item must be visible on mouse over.");
    }
    
}
