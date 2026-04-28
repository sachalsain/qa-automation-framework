package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.StatusCodesPage;
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
public class StatusCodesTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(StatusCodesTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Status Codes Test.")
    @Description("Test to check Status Codes Page work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void StatusCodesTest() {
        logger.info("Testing Status Codes");
        logger.debug("Creating StatusCodesPage instance.");
        StatusCodesPage statusCodesPage = new StatusCodesPage(page);
        logger.debug("Opening Shadow DOM Page.");
        statusCodesPage.open();
        logger.debug("Checking the '200' Link for the status code of 200.");
        Assert.assertEquals(statusCodesPage.getStatusCode(200), 200, "The Status code of 200 must be matched");
        
        logger.debug("Checking the '301' Link for the status code of 301.");
        Assert.assertEquals(statusCodesPage.getStatusCode(301), 301, "The Status code of 301 must be matched");
        
        logger.debug("Checking the '404' Link for the status code of 404.");
        Assert.assertEquals(statusCodesPage.getStatusCode(404), 404, "The Status code of 404 must be matched");
        
        logger.debug("Checking the '500' Link for the status code of 500.");
        Assert.assertEquals(statusCodesPage.getStatusCode(500), 500, "The Status code of 500 must be matched");
        
    }
}
