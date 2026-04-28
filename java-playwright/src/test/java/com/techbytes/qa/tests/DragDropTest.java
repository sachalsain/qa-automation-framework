package com.techbytes.qa.tests;

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

    private static final Logger logger = LoggerFactory.getLogger(AddDeleteTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups="regression")
    @Story("Check Drag Drop Elements")
    @Description("Verify if Drag Drop Elements are working properly")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void dragDropTest() {
        logger.info("Testing Drag / Drop functionality");
        logger.debug("Creating DragDropPage instance.");
        DragDropPage dragDropPage = new DragDropPage(page);
        logger.debug("Opening Drag / Drop Page.");
        dragDropPage.open();
        logger.debug("Verify if box 1 has A.");
        Assert.assertEquals(dragDropPage.getTextbox1(), "A", "Box 1 should contain 'A'");
        logger.debug("Verify if box 2 has B.");
        Assert.assertEquals(dragDropPage.getTextbox2(), "B", "Box 2 should contain 'B'");
        logger.debug("Perform Drag Drop.");
        dragDropPage.performDragDrop();
        logger.debug("Verify if box 1 has B after Drag Drop.");
        Assert.assertEquals(dragDropPage.getTextbox1(), "B", "Box 1 should contain 'B' after Drag Drop");
        logger.debug("Verify if box 2 has A after Drag Drop.");
        Assert.assertEquals(dragDropPage.getTextbox2(), "A", "Box 2 should contain 'A' after Drag Drop");
    }
}
