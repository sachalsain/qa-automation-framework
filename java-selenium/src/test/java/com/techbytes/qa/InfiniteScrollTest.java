package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Infinite Scroll Page")
    @Description("Testing Infinite Scroll Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void infiniteScrollTest() {
        logger.info(" -> Starting Test of Infinite Scroll Page");
        logger.debug(" -> Creating InfiniteScrollPage instance.");
        InfiniteScrollPage infiniteScrollPage = new InfiniteScrollPage(getDriver());
        logger.debug(" -> Opening Infinite Scroll Page.");
        infiniteScrollPage.open();
        
        logger.debug(" -> Verifying the count of the para before scroll.");
        Assert.assertEquals(infiniteScrollPage.getParaCount(), 1, "A single content is to be displayed on page load.");
        logger.debug(" -> Verified the count of the para before scroll.");
        
        logger.debug(" -> Scrolling down the page.");
        infiniteScrollPage.scrollDown();
        
        logger.debug(" -> Verifying the count of the para after scroll.");
        Assert.assertEquals(infiniteScrollPage.getParaCount(), 2, "2 contents are to be displayed after scroll.");
        logger.debug(" -> Verified the count of the para after scroll.");
    }
    
}
