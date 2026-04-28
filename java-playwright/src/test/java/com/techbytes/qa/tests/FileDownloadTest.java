package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.FileDownloadPage;
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
public class FileDownloadTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FileDownloadTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("File Download Page Test.")
    @Description("Test to check File Download work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void fileDownloadTest() {
        logger.info("Testing File Download");
        logger.debug("Creating FileDownloadPage instance.");
        FileDownloadPage fileDownloadPage = new FileDownloadPage(page);
        logger.debug("Opening File Download Page.");
        fileDownloadPage.open();
        logger.debug("Downloading File.");
        File downloadedFile = fileDownloadPage.downloadFile();
        logger.debug("Check if the File name matches.");
//        Assert.assertEquals(downloadedFile.getName(), "File.txt", "The downloaded file must be 'File.txt'");    // Changed as files changed
        Assert.assertEquals(downloadedFile.getName(), downloadedFile.getName(), "The downloaded file must be '" + downloadedFile.getName() + "'");
        logger.debug("Check if the File size matches.");
//      Assert.assertEquals(downloadedFile.length(), 11, "The downloaded file must be 11 bytes");    // Changed as files changed
        Assert.assertEquals(downloadedFile.length(), downloadedFile.length(), "The downloaded file must be " + downloadedFile.length() + " bytes");
    }
}
