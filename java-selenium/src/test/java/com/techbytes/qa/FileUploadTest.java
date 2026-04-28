package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.FileUploadPage;
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
public class FileUploadTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing File Upload Page")
    @Description("Testing File Upload Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void fileUploadTest() {
        logger.info(" -> Starting Test of File Upload Page");
        logger.debug(" -> Creating FileUploadPage instance.");
        FileUploadPage fileUploadPage = new FileUploadPage(getDriver());
        logger.debug(" -> Opening File Upload Page.");
        fileUploadPage.open();
        logger.debug(" -> Uploading File.");
        fileUploadPage.uploadFile();
        
        logger.debug(" -> Verifying if the Title is success.");
        Assert.assertEquals(fileUploadPage.getTitle(), "File Uploaded!", "The Title must say 'File Uploaded!'");
        logger.debug(" -> Verified that the Title is success.");
        
        logger.debug(" -> Verifying if the Body is success.");
        Assert.assertEquals(fileUploadPage.getBody().strip(), "uploadTestFile.txt", "The file name must be 'uploadTestFile.txt'");
        logger.debug(" -> Verified that the Body is success.");
    }
    
}
