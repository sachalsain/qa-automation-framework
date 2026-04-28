package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.DragDropPage;
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
public class DragDropTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DragDropTest.class);
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Drag Drop Page")
    @Description("Testing Drag Drop Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void dragDropTest() {
        logger.info(" -> Starting Test of Drag Drop Page");
        logger.debug(" -> Creating DragDropPage instance.");
        DragDropPage dragDropPage = new DragDropPage(getDriver());
        logger.debug(" -> Opening Drag Drop Page.");
        dragDropPage.open();
        
        logger.debug(" -> Verifying if box 1 has A.");
        Assert.assertEquals(dragDropPage.getTextbox1(), "A", "Box 1 should contain 'A'");
        logger.debug(" -> Verified that box 1 has A.");
        
        logger.debug(" -> Verifying if box 2 has B.");
        Assert.assertEquals(dragDropPage.getTextbox2(), "B", "Box 2 should contain 'B'");
        logger.debug(" -> Verified that box 2 has B.");
        
        logger.debug(" -> Perform Drag Drop.");
        dragDropPage.performDragDrop();
        
        logger.debug(" -> Verifying if box 1 has B after Drag Drop.");
        Assert.assertEquals(dragDropPage.getTextbox1(), "B", "Box 1 should contain 'B' after Drag Drop");
        logger.debug(" -> Verified that box 1 has B after Drag Drop.");
        
        logger.debug(" -> Verifying if box 2 has A after Drag Drop.");
        Assert.assertEquals(dragDropPage.getTextbox2(), "A", "Box 2 should contain 'A' after Drag Drop");
        logger.debug(" -> Verified that box 2 has A after Drag Drop.");
    }
}
