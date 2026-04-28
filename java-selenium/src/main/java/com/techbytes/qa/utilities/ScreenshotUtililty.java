package com.techbytes.qa.utilities;

import com.google.common.io.Files;
import com.techbytes.qa.core.ConfigurationLoader;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ScreenshotUtililty {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtililty.class);

    public static byte[] takeScreenshot(WebDriver driver, ITestResult result) {
        logger.info(" -> Taking screenshot.");
        // 1. Capture screenshot as byte array
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        logger.debug(" -> Creating image file of screenshot.");
        File directory = new File(ConfigurationLoader.get("screenshotPath"));
        if (!directory.exists()) {
            logger.debug(" -> Screenshot directory does not exist. Creating it.");
            directory.mkdirs(); // IMPORTANT
        }
        File destFile = new File(directory, result.getName() + Instant.now().toEpochMilli() + ".png");
        try {
            Files.write(screenshot, destFile);
            logger.debug(" -> Saved image file.");
        } catch (IOException ex) {
            logger.error(" -> Could not save image file. Reason: {}", ex.getMessage());
        }
        return screenshot;
    }
}
