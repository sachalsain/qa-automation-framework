package com.techbytes.qa.pages;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class SecureDownloadPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(SecureDownloadPage.class);

    private final String fileLinkContainer = "#content .example a:nth-of-type(13)";

    public SecureDownloadPage(Page page) {
        logger.info("Setting up the Secure Download Page.");
        super(page);
    }
    
    public void open() {
        logger.info("Navigating to the Secure Download Page URL.");
        page.navigate(ConfigurationLoader.get("secureDownloadUrl"));
    }
    
    public String getRedirectedUrl() {
        logger.info("Returning the Redirected Url.");
        return page.url();
    }

    public File downloadFile() {
        logger.info("Downloading 13th File.");
        Download dldFile = page.waitForDownload(() -> {
            page.locator(fileLinkContainer).click();
        });
        logger.debug("Defining target directory.");
        Path targetDir = Paths.get("target/downloads");
        logger.debug("Ensuring Directory exists.");
        if (!targetDir.toFile().exists()) {
            logger.debug("Directory not found. Creating directory.");
            targetDir.toFile().mkdirs();
        }
        Path destination = targetDir.resolve(dldFile.suggestedFilename());
        dldFile.saveAs(destination);
        return destination.toFile();
    }
}
