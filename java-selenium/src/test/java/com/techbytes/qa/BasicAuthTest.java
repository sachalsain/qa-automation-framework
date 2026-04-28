package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.BasicAuthPage;
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
public class BasicAuthTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Basic Authentication Page")
    @Description("Testing Basic Authentication Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void basicAuthTest() {
        logger.info(" -> Starting Test of Basic Authentication Page.");
        logger.debug(" -> Creating BasicAuthPage instance.");
        BasicAuthPage basicAuthPage = new BasicAuthPage(getDriver());
        logger.debug(" -> Opening BasicAuthPage Page.");
        basicAuthPage.open();
        logger.debug(" -> Verifying if the page contains SUCCESS message.");
        String message = basicAuthPage.getParagraphText();
        Assert.assertTrue(message.equalsIgnoreCase("Congratulations! You must have the proper credentials."), "Expected SUCCESS message but got: " + message);
        logger.debug(" -> Verified that the page contains SUCCESS message.");

//        logger.debug("Verify if the Authentication request returns 200.");
//        try {
//            int statusCode = ApiUtils.getStatusCode(basicAuthPage.getUrl());
//            Assert.assertEquals(statusCode, 200, "Expected 200 status code but got: " + statusCode);
//        } catch (IOException | InterruptedException ex) {
//                logger.error("Authentication Request failed. Reason: {}", ex.getMessage());
//        }
//        logger.debug("Verified that the Authentication request returns 200.");
    }
}
