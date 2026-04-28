package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Floating Menu Page")
    @Description("Testing Floating Menu Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void floatingMenuTest() {
        logger.info(" -> Starting Test of Floating Menu Page");
        logger.debug(" -> Creating FloatingMenuPage instance.");
        FloatingMenuPage floatingMenuPage = new FloatingMenuPage(getDriver());
        logger.debug(" -> Opening Floating Menu Page.");
        floatingMenuPage.open();
        
        logger.debug(" -> Verifying if menu is visible.");
        Assert.assertTrue(floatingMenuPage.isMenuVisible(), "The menu must be visible");
        logger.debug(" -> Verified that menu is visible.");
        
        logger.debug(" -> Scrolling the page down.");
        floatingMenuPage.scrollDown();
        
        logger.debug(" -> Verifying if menu is visible after scroll.");
        Assert.assertTrue(floatingMenuPage.isMenuVisible(), "The menu must be visible");
        logger.debug(" -> Verified that menu is visible after scroll.");
    }
    
}
