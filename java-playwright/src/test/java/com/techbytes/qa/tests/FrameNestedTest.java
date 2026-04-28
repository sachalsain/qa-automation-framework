package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.FrameNestedPage;
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
public class FrameNestedTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FrameNestedTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Nested Frames Page Test.")
    @Description("Test to check Nested Frames work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void nestedFramesTest() {
        logger.info("Testing Nested Frames");
        logger.debug("Creating NestedFramesPage instance.");
        FrameNestedPage nestedFramesPage = new FrameNestedPage(page);
        logger.debug("Opening Nested Frames Page.");
        nestedFramesPage.open();
        logger.debug("Check if contents of top-left frame matches.");
        Assert.assertTrue(nestedFramesPage.getLeftFrameContent().toUpperCase().contains("LEFT"), "The text content must be 'LEFT'");
        logger.debug("Check if contents of top-middle frame matches.");
        Assert.assertTrue(nestedFramesPage.getMiddleFrameContent().toUpperCase().contains("MIDDLE"), "The text content must be 'MIDDLE'");
        logger.debug("Check if contents of top-right frame matches.");
        Assert.assertTrue(nestedFramesPage.getRightFrameContent().toUpperCase().contains("RIGHT"), "The text content must be 'RIGHT'");
        logger.debug("Check if contents of bottom frame matches.");
        Assert.assertTrue(nestedFramesPage.getBottomFrameContent().toUpperCase().contains("BOTTOM"), "The text content must be 'BOTTOM'");
        
    }
    
}
