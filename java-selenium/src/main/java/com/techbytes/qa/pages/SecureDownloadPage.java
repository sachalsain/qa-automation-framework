package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.utilities.FileUtilities;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class SecureDownloadPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(SecureDownloadPage.class);

    public SecureDownloadPage(WebDriver driver) {
        logger.info(" -> Setting up the Secure Download Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Secure Download Page...");
        openUrl(ConfigurationLoader.get("secureDownloadUrl"));
    }
    
    public String getRedirectedUrl() {
        logger.info(" -> Returning the Redirected Url.");
        return driver.getCurrentUrl();
    }

    public File downloadFile() throws IOException {
        logger.info(" -> Preparing for File download.");
        logger.debug(" -> Setting the Path of the system Downloads folder.");
        String defaultDownloadPath = System.getProperty("user.home") + File.separator + "Downloads";
        logger.debug(" -> Setting the Path to the target Downloads folder.");
        String projectDownloadPath = "target/downloads";
        logger.debug(" -> Cleaning up the target Downloads folder.");
        FileUtilities fileUtilities = new FileUtilities();
        fileUtilities.cleanupDownloadFolder(projectDownloadPath, ".*"); // Change extension as needed
        logger.debug(" -> Downloading 3rd File.");
        driver.findElement(By.cssSelector("#content .example a:nth-of-type(3)")).click();
        logger.debug(" -> Setting wait for downloading file.");
        // 3. Wait for file in Default System Folder
        FluentWait<File> wait = new FluentWait<>(new File(defaultDownloadPath))
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        logger.debug(" -> Setting current Time.");
        long startTime = System.currentTimeMillis();
        logger.debug(" -> Downloading File.");
        File downloadedFile = wait.until(dir -> {
            File[] files = dir.listFiles();
            if (files == null) {
                return null;
            }
            return Arrays.stream(files)
                    .filter(f -> f.lastModified() > startTime && !f.getName().endsWith(".crdownload"))
                    .findFirst()
                    .orElse(null);
        });

        if (downloadedFile == null) {
            throw new IOException("Download timed out.");
        }
        
        logger.debug(" -> Checking if the target Downloads folder exists.");
        Path targetDir = Paths.get(projectDownloadPath);
        if (!Files.exists(targetDir)) {
        logger.debug(" -> Target Downloads folder not found. Creating the folder");
            Files.createDirectories(targetDir);
        }

        logger.debug(" -> Moving the downloaded file to the target Downloads folder.");
        // 5. Move to project folder (preserves original filename)
        Path destination = targetDir.resolve(downloadedFile.getName());
        Files.move(downloadedFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        logger.debug(" -> Returning Saved File.");
        return destination.toFile();
    }
    
}
