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
public class FrameInlinePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FrameInlinePage.class);

    public FrameInlinePage(WebDriver driver) {
        logger.info(" -> Setting up the Frame Inline Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Frame Inline Page...");
        openUrl(ConfigurationLoader.get("iFrameUrl"));
    }

    public String getBodyContent() {
        logger.info(" -> Returning the content of the left frame.");
        logger.debug(" -> Switching focus to the iframe using its locator");
        //  Remove HashTag as it treats it a part of the string.
        driver.switchTo().frame("mce_0_ifr");
        logger.debug(" -> Fetching the text and removing any whitespace.");
        String text = driver.findElement(By.cssSelector("#tinymce p")).getText().trim();
        logger.debug(" -> Switching back to the main page content.");
        driver.switchTo().defaultContent();
        return text;
    }

}
