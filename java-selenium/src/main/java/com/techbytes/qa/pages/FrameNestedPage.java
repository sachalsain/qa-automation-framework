package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class FrameNestedPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FrameNestedPage.class);

    public FrameNestedPage(WebDriver driver) {
        logger.info(" -> Setting up the Frame Nested Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Frame Nested Page...");
        openUrl(ConfigurationLoader.get("nestedFramesUrl"));
    }
    
    public String getLeftFrameContent() {
        logger.info(" -> Returning the content of the left frame.");
        String text = driver.switchTo().frame("frame-top").switchTo().frame("frame-left").findElement(By.cssSelector("body")).getText().trim();
        logger.debug(" -> Switching back to the main page content.");
        driver.switchTo().defaultContent();
        return text;
    }
    
    public String getMiddleFrameContent() {
        logger.info(" -> Returning the content of the middle frame.");
        String text = driver.switchTo().frame("frame-top").switchTo().frame("frame-middle").findElement(By.cssSelector("body")).getText().trim();
        logger.debug(" -> Switching back to the main page content.");
        driver.switchTo().defaultContent();
        return text;
    }
    
    public String getRightFrameContent() {
        logger.info(" -> Returning the content of the right frame.");
        String text = driver.switchTo().frame("frame-top").switchTo().frame("frame-right").findElement(By.cssSelector("body")).getText().trim();
        logger.debug(" -> Switching back to the main page content.");
        driver.switchTo().defaultContent();
        return text;
    }
    
    public String getBottomFrameContent() {
        logger.info(" -> Returning the content of the bottom frame.");
        String text = driver.switchTo().frame("frame-bottom").findElement(By.cssSelector("body")).getText().trim();
        logger.debug(" -> Switching back to the main page content.");
        driver.switchTo().defaultContent();
        return text;
    }
    
}
