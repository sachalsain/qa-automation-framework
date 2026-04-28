package com.techbytes.qa.listeners;

import com.techbytes.qa.core.DriverFactory;
import com.techbytes.qa.utilities.ScreenshotUtililty;
import io.qameta.allure.Attachment;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(" -> {} test passed.", result.getName());
//        logger.info(" -> Requesting screeshot to attach.");
//        // Capture the screenshot as bytes
//        byte[] screenshot = attachScreenshot(result);
//        if (screenshot != null && screenshot.length > 0) {
//            logger.info(" -> Success Screenshot attached.");
//        } else {
//            logger.error(" -> Failed to attach Success screenshot.");
//        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info(" -> {} test failed. Reason: {}", result.getName(), result.getThrowable());
        logger.debug(" -> Requesting screeshot to attach.");
        // Capture the screenshot as bytes
        byte[] screenshot = attachScreenshot(result);
        // Check if the screenshot was successfully captured (not null/empty)
        if (screenshot != null && screenshot.length > 0) {
            logger.debug(" -> Failure Screenshot attached to Allure.");
            logger.debug(" -> Attaching Failure Screenshot log.");
            saveTextLog(result.getThrowable());
        } else {
            logger.error(" -> Failed to capture Failure screenshot.");
        }
    }

    // @Attachment(value = "Failure Screenshot", type = "image/png")
    // public byte[] attachScreenshot(ITestResult result) {
    //     logger.info(" -> Creating screenshot.");
    //     return ScreenshotUtililty.takeScreenshot(DriverFactory.get(), result);
    // }
    
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] attachScreenshot(ITestResult result) {
        logger.info("Attaching screenshot.");
        WebDriver driver = DriverFactory.get();

        if (driver == null) {
            logger.error(" -> WebDriver is null. Cannot capture screenshot.");
            return new byte[0];
        }

        return ScreenshotUtililty.takeScreenshot(driver, result);
    }


    @Attachment(value = "Error Log", type = "text/plain")
    public String saveTextLog(Throwable exception) {
        logger.info("Attaching Log.");
        return exception.getMessage();
    }
}
