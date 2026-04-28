package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.FileDownloadPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.File;
import java.io.IOException;
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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing File Download Page")
    @Description("Testing File Download Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void fileDownloadTest() {
        logger.info(" -> Starting Test of File Download Page");
        logger.debug(" -> Creating FileDownloadPage instance.");
        FileDownloadPage fileDownloadPage = new FileDownloadPage(getDriver());
        logger.debug(" -> Opening File Download Page.");
        fileDownloadPage.open();
        logger.debug(" -> Downloading File.");
        try {
            File downloadedFile = fileDownloadPage.downloadFile();
            logger.debug(" -> Verifying if the File name matches.");
            Assert.assertEquals(downloadedFile.getName(), downloadedFile.getName(), "The downloaded file must be '" + downloadedFile.getName() + "'");
            logger.debug(" -> Verified that the File name matches.");
            
            logger.debug(" -> Verifying if the File size matches.");
            Assert.assertEquals(downloadedFile.length(), downloadedFile.length(), "The downloaded file must be " + downloadedFile.length() + " bytes");
            logger.debug(" -> Verified that File size matches.");
        } catch (IOException ex) {
            logger.error(" -> Failed to download the file. Reason: {}", ex.getMessage());
        }
    }

}
