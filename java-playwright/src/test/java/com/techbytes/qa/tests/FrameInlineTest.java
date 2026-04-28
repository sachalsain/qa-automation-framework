package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.FrameInlinePage;
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
public class FrameInlineTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FrameInlineTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Inline Frames Page Test.")
    @Description("Test to check Inline Frames work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void inlineFramesTest() {
        logger.info("Testing Nested Frames");
        logger.debug("Creating NestedFramesPage instance.");
        FrameInlinePage frameInlinePage = new FrameInlinePage(page);
        logger.debug("Opening Nested Frames Page.");
        frameInlinePage.open();
        logger.debug("Check if contents of top-left frame matches.");
        Assert.assertTrue(frameInlinePage.getBodyContent().toLowerCase().contains("content goes here"), "The text content must be 'content goes here'");
    }
    
}
