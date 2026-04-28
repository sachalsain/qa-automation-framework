package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Redirection Page")
    @Description("Testing Redirection Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void redirectionTest() {
        logger.info(" -> Starting Test of Redirection Page");
        logger.debug(" -> Creating RedirectionPage instance.");
        RedirectionPage page = new RedirectionPage(getDriver());
        logger.debug(" -> Opening Redirection Page.");
        page.open();
        
        logger.debug(" -> Cliking the link.");
        page.clickLink();
        
        logger.debug(" -> Verifying if the URL contains expected value.");
        Assert.assertTrue(page.getRedirectedUrl().contains("status_codes"), "The redirected url must contain 'status_codes'.");
        logger.debug(" -> Verified that the URL contains expected value.");
        
        logger.debug(" -> Verifying if the redirected page contains expected Heading.");
        Assert.assertTrue(page.getredirectedHeading().contains("Status Codes"), "The redirected page must contain 'Status Codes' heading.");
        logger.debug(" -> Verified that the redirected page contains expected Heading.");
    }
    
}
