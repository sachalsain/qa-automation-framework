package com.techbytes.qa.utilities;

import com.microsoft.playwright.Page;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.core.PlaywrightFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ScreenshotUtililty {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtililty.class);

    public static byte[] takeScreenshot(ITestResult result) {
        logger.info("Screenshot rewuested.");
        logger.debug("Fetching Page object.");
        Page page = PlaywrightFactory.getPage();

        // 1. Capture screenshot as byte array
        logger.debug("Creating byte array of screenshot from page object.");
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));

        //  2. Prepare Directory
        logger.debug("Creating image file of screenshot.");
        File directory = new File(ConfigurationLoader.get("screenshotPath"));
        if (!directory.exists()) {
            logger.debug("Screenshot directory does not exist. Creating it.");
            directory.mkdirs(); // IMPORTANT
        }
        //  3. Create unique filename
        logger.debug("Creating unique filename for the file.");
        String fileName = result.getName() + "_" + Instant.now().toEpochMilli() + ".png";
        logger.debug("Creating path to save the file.");
        Path destination = Paths.get(directory.getAbsolutePath(), fileName);

        //  4. Save file
        try {
            Files.write(destination, screenshot);
            logger.debug(" -> Saved image file at {}.", destination);
        } catch (IOException ex) {
            logger.error(" -> Could not save image file. Reason: {}", ex.getMessage());
        }
        logger.debug("Returning byte array of the screenshot.");
        return screenshot;
    }
}
