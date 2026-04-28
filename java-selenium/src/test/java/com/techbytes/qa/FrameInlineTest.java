package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Frame Inline Page")
    @Description("Testing Frame Inline Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void frameInlineTest() {
        logger.info(" -> Starting Test of Frame Inline Page");
        logger.debug(" -> Creating FrameInlinePage instance.");
        FrameInlinePage frameInlinePage = new FrameInlinePage(getDriver());
        logger.debug(" -> Opening Frame Inline Page.");
        frameInlinePage.open();
        
        logger.debug(" -> Verifying if contents of inline frame matches.");
        Assert.assertTrue(frameInlinePage.getBodyContent().toLowerCase().contains("content goes here"), "The text content must contain 'content goes here'");
        logger.debug(" -> Verified that contents of inline frame matches.");
    }
    
}
