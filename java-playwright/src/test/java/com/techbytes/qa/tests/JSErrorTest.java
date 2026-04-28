package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.JSErrorPage;
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
public class JSErrorTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(JSErrorTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("JSError Test.")
    @Description("Test to check JavaScript Error work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void jSErrorTest() {
        logger.info("Testing JavaScript Error");
        logger.debug("Creating JSErrorPage instance.");
        JSErrorPage jSErrorPage = new JSErrorPage(page);
        logger.debug("Opening JavaScripts Error Page.");
        jSErrorPage.open();
        logger.debug("Checking if errors is not empty.");
        Assert.assertFalse(jSErrorPage.getErrors().isEmpty(), "There must be errors.");
        logger.debug("Checking if specific error is raised.");
        Assert.assertTrue(jSErrorPage.getErrors().stream().anyMatch(e -> e.contains("undefined (reading 'xyz')")), "the error must contain: undefined (reading 'xyz')");
    }
}
