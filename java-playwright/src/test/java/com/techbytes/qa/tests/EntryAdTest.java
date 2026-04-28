package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.EntryAdPage;
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
public class EntryAdTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(EntryAdTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Entry Ad Page Test.")
    @Description("Test to check Entry Ad Page work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void dynamicContentTest() {
        logger.info("Testing Entry Ad");
        logger.debug("Creating EntryAdPage instance.");
        EntryAdPage entryAdPage = new EntryAdPage(page);
        logger.debug("Opening Entry Ad Page.");
        entryAdPage.open();
        logger.debug("Checking if the Window is Visible.");
        Assert.assertTrue(entryAdPage.isWindowVisible(), "The Window must be visisble on first load.");
        logger.debug("Checking if proper title is displayed.");
        Assert.assertTrue(entryAdPage.isWindowVisible() && entryAdPage.getWindowTitle().toLowerCase().contains("modal window"), "The Title must contain 'modal window'.");
        logger.debug("Checking if proper body is displayed.");
        Assert.assertTrue(entryAdPage.isWindowVisible() && entryAdPage.getWindowText().toLowerCase().contains("used to encourage"), "The Body must contain 'used to encourage'.");
        logger.debug("Checking if proper footer is displayed.");
        Assert.assertTrue(entryAdPage.isWindowVisible() && entryAdPage.getWindowClose().toLowerCase().contains("close"), "The Footer must contain 'close'.");
        logger.debug("Closing the window.");
        entryAdPage.closeWindow();
        logger.debug("Checking if the Window is Visible.");
        Assert.assertFalse(entryAdPage.isWindowHidden(), "The Window must be not be visible after clicking close.");
        logger.debug("Reloading the page to check if window is displayed again.");
        entryAdPage.reloadPage();
        logger.debug("Checking if the Window is Visible.");
        Assert.assertFalse(entryAdPage.isWindowHidden(), "The Window must be not be visible after reloading the page.");
    }
    
}
