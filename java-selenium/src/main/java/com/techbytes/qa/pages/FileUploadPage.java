package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class FileUploadPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadPage.class);

    public FileUploadPage(WebDriver driver) {
        logger.info(" -> Setting up the File Upload Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the File Upload Page...");
        openUrl(ConfigurationLoader.get("uploadFileUrl"));
    }
    
    public void uploadFile() {
        logger.info(" -> Uploading File.");
        logger.debug(" -> Adding file.");
        driver.findElement(By.cssSelector("#file-upload")).sendKeys(new File("src/test/resources/uploadTestFile.txt").getAbsolutePath());
        logger.debug(" -> Clicking the upload button.");
        driver.findElement(By.cssSelector("#file-submit")).click();
    }

    public String getTitle() {
        logger.info(" -> Returning Title of page.");
        return driver.findElement(By.cssSelector("#content .example h3")).getText();
    }

    public String getBody() {
        logger.info(" -> Returning Body of page.");
        return driver.findElement(By.cssSelector("#uploaded-files")).getText();
    }
    
}
