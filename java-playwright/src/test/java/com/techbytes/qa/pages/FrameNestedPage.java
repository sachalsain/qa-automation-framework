package com.techbytes.qa.pages;

import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class FrameNestedPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FrameNestedPage.class);

    private final String topFrame = "frame[name='frame-top']";
    private final String topLeftFrame = "frame[name='frame-left']";
    private final String topMiddleFrame = "frame[name='frame-middle']";
    private final String topRightFrame = "frame[name='frame-right']";
    private final String bottomFrame = "frame[name='frame-bottom']";
    
    public FrameNestedPage(Page page) {
        logger.info("Setting up the Nested Frames Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Nested Frames Page URL.");
        page.navigate(ConfigurationLoader.get("nestedFramesUrl"));
    }
    
    public String getLeftFrameContent() {
        logger.info("Returning the content of the left frame.");
        return page.frameLocator(topFrame).frameLocator(topLeftFrame).locator("body").textContent().strip();
    }
    
    public String getMiddleFrameContent() {
        logger.info("Returning the content of the middle frame.");
        return page.frameLocator(topFrame).frameLocator(topMiddleFrame).locator("body").textContent().strip();
    }
    
    public String getRightFrameContent() {
        logger.info("Returning the content of the right frame.");
        return page.frameLocator(topFrame).frameLocator(topRightFrame).locator("body").textContent().strip();
    }
    
    public String getBottomFrameContent() {
        logger.info("Returning the content of the bottom frame.");
        return page.frameLocator(bottomFrame).locator("body").textContent().strip();
    }
    
}
