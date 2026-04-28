package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Frame Nested Page")
    @Description("Testing Frame Nested Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void frameNestedTest() {
        logger.info(" -> Starting Test of Frame Nested Page");
        logger.debug(" -> Creating FrameNestedPage instance.");
        FrameNestedPage frameNestedPage = new FrameNestedPage(getDriver());
        logger.debug(" -> Opening Frame Nested Page.");
        frameNestedPage.open();
        logger.debug(" -> Verifying if contents of top-left frame matches.");
        Assert.assertTrue(frameNestedPage.getLeftFrameContent().toUpperCase().contains("LEFT"), "The text content must be 'LEFT'");
        logger.debug(" -> Verified that contents of top-left frame matches.");
        
        logger.debug(" -> Verifying if contents of top-middle frame matches.");
        Assert.assertTrue(frameNestedPage.getMiddleFrameContent().toUpperCase().contains("MIDDLE"), "The text content must be 'MIDDLE'");
        logger.debug(" -> Verified that contents of top-middle frame matches.");
        
        logger.debug(" -> Verifying if contents of top-right frame matches.");
        Assert.assertTrue(frameNestedPage.getRightFrameContent().toUpperCase().contains("RIGHT"), "The text content must be 'RIGHT'");
        logger.debug(" -> Verified that contents of top-right frame matches.");
        
        logger.debug(" -> Verifying if contents of bottom frame matches.");
        Assert.assertTrue(frameNestedPage.getBottomFrameContent().toUpperCase().contains("BOTTOM"), "The text content must be 'BOTTOM'");
        logger.debug(" -> Verified that contents of bottom frame matches.");
    }
    
}
