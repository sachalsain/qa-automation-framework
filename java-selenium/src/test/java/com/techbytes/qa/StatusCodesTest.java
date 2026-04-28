package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.StatusCodesPage;
import com.techbytes.qa.utilities.ApiUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.IOException;
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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Redirection Page")
    @Description("Testing Redirection Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void statusCodesTest() {
        logger.info(" -> Starting Test of Redirection Page");
        logger.debug(" -> Creating RedirectionPage instance.");
        StatusCodesPage page = new StatusCodesPage(getDriver());
        logger.debug(" -> Opening Redirection Page.");
        page.open();
        try {
//          logger.debug("Verifying the '200' Link for the status code of 200.");
//          Assert.assertEquals(ApiUtils.getStatusCode(page.getBaseUrl() + "/200"), 200, "The Status code of 200 must be matched");
//          logger.debug("Verified the '200' Link for the status code of 200.");
//        
//          logger.debug("Verifying the '301' Link for the status code of 301.");
//          Assert.assertEquals(ApiUtils.getStatusCode(page.getBaseUrl() + "/301"), 301, "The Status code of 301 must be matched");
//          logger.debug("Verified the '301' Link for the status code of 301.");

            logger.debug(" -> Verifying the '404' Link for the status code of 404.");
            Assert.assertEquals(ApiUtils.getStatusCode(page.getBaseUrl() + "/404"), 404, "The Status code of 404 must be matched");
            logger.debug(" -> Verified the '404' Link for the status code of 404.");

            logger.debug(" -> Verifying the '500' Link for the status code of 500.");
            Assert.assertEquals(ApiUtils.getStatusCode(page.getBaseUrl() + "/500"), 500, "The Status code of 500 must be matched");
            logger.debug(" -> Verified the '500' Link for the status code of 500.");
        } catch (IOException | InterruptedException ex) {
            logger.error(" -> Request failed. Reason: {}", ex.getMessage());
        }
    }

}
