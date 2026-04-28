package com.techbytes.qa.pages;

import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class FileUploadPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadPage.class);

    private final String browseBtn = "#file-upload";
    private final String uploadBtn = "#file-submit";
    private final String successMsgTitle = "#content .example h3";
    private final String successMsgBody = "#uploaded-files";

    public FileUploadPage(Page page) {
        logger.info("Setting up the File Upload Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the File Upload Page URL.");
        page.navigate(ConfigurationLoader.get("uploadFileUrl"));
    }
    
    public void uploadFile() {
        logger.info("Uploading File.");
        logger.debug("Adding file.");
        page.setInputFiles(browseBtn, Paths.get("src/test/resources/uploadTestFile.txt"));
        logger.debug("Clicking the upload button.");
        page.locator(uploadBtn).click();
    }

    public String getTitle() {
        logger.info("Returning Title of page.");
        return page.locator(successMsgTitle).textContent();
    }

    public String getBody() {
        logger.info("Returning Body of page.");
        return page.locator(successMsgBody).textContent();
    }
    
}
