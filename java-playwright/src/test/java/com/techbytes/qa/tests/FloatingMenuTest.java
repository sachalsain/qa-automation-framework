package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.FloatingMenuPage;
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
public class FloatingMenuTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FloatingMenuTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Floating Menu Page Test.")
    @Description("Test to check Floating Menu work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void floatingMenuTest() {
        logger.info("Testing Floating Menu");
        logger.debug("Creating FloatingMenuPage instance.");
        FloatingMenuPage floatingMenuPage = new FloatingMenuPage(page);
        logger.debug("Opening Floating Menu Page.");
        floatingMenuPage.open();
        logger.debug("Check if menu is visible.");
        Assert.assertTrue(floatingMenuPage.isMenuVisible(), "The menu must be visible");
        logger.debug("Scrolling the page down.");
        floatingMenuPage.scrollDown();
        logger.debug("Check if menu is visible after scroll.");
        Assert.assertTrue(floatingMenuPage.isMenuVisible(), "The menu must be visible");
    }
    
}
