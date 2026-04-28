package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.ContextMenuPage;
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
public class ContextMenuTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ContextMenuTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Alert Dialog Page")
    @Description("Testing Alert Dialog Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void alertDialogTest() {
        logger.info("Testing Alert Box");
        logger.debug("Creating ContextMenuPage instance.");
        ContextMenuPage contextMenuPage = new ContextMenuPage(page);
        logger.debug("Opening ContextMenuPage Page.");
        contextMenuPage.open();
        logger.debug("Assertaining if the message displayed in Alert Box is correct.");
        Assert.assertEquals(contextMenuPage.getTextOfAlert(), "You selected a context menu", "The message displayed should match.");
    }
    
}
