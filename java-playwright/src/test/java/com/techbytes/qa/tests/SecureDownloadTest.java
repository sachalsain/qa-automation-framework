package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.SecureDownloadPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class SecureDownloadTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SecureDownloadTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Secure Download testing")
    @Description("Secure Download testing")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Tahreem J. Naseem")
    public void SecureDownloadTest() {
        logger.info("Testing Secure Download");
        logger.debug("Setting up Auth Playwright instance.");
        setupWithAuth("admin", "admin");
        logger.debug("Creating SecureDownloadPage instance.");
        SecureDownloadPage secureDownloadPage = new SecureDownloadPage(page);
        logger.debug("Opening page.");
        secureDownloadPage.open();
        logger.debug("Checking if the Url contains expected value.");
        Assert.assertTrue(secureDownloadPage.getRedirectedUrl().contains("download_secure"), "The redirected url must contain 'download_secure'.");
        logger.debug("Downloading File.");
        File downloadedFile = secureDownloadPage.downloadFile();
        logger.debug("Check if the File name matches.");
        Assert.assertEquals(downloadedFile.getName(), downloadedFile.getName(), "The downloaded file must be '" + downloadedFile.getName() + "'");
        logger.debug("Check if the File size matches.");
        Assert.assertEquals(downloadedFile.length(), downloadedFile.length(), "The downloaded file must be " + downloadedFile.length() + " bytes");
    }
}
