package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.InfiniteScrollPage;
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
public class InfiniteScrollTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(InfiniteScrollTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Infinite Scroll Test.")
    @Description("Test to check Infinite Scroll work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void infiniteScrollTest() {
        logger.info("Testing Infinite Scroll");
        logger.debug("Creating InfiniteScrollPage instance.");
        InfiniteScrollPage infiniteScrollPage = new InfiniteScrollPage(page);
        logger.debug("Opening Infinite Scroll Page.");
        infiniteScrollPage.open();
        logger.debug("Check the count of the para before scroll.");
        Assert.assertEquals(infiniteScrollPage.getParaCount(), 1, "A single content is to be displayed on page load.");
        logger.debug("Scrolling down the page.");
        infiniteScrollPage.scrollDown();
        logger.debug("Check the count of the para after scroll.");
        Assert.assertEquals(infiniteScrollPage.getParaCount(), 2, "2 contents are to be displayed after scroll.");
    }
    
}
