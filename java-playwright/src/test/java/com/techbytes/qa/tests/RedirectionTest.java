package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.RedirectionPage;
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
public class RedirectionTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(RedirectionTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Redirection Test.")
    @Description("Test to check Redirection Page work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void KeyPressTest() {
        logger.info("Testing Redirection");
        logger.debug("Creating RedirectionPage instance.");
        RedirectionPage redirectionPage = new RedirectionPage(page);
        logger.debug("Opening Redirection Page.");
        redirectionPage.open();
        logger.debug("Cliking the link.");
        redirectionPage.clickLink();
        logger.debug("Checking if the Url contains expected value.");
        Assert.assertTrue(redirectionPage.getRedirectedUrl().contains("status_codes"), "The redirected url must contain 'status_codes'.");
        logger.debug("Checking if the redirected page contains expected Heading.");
        Assert.assertTrue(redirectionPage.getredirectedHeading().contains("Status Codes"), "The redirected page must contain 'Status Codes' heading.");
    }
}
