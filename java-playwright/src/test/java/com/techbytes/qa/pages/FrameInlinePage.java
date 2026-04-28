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
public class FrameInlinePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FrameInlinePage.class);

    private final String iFrame = "#mce_0_ifr";
    private final String textpara = "body p";
    
    public FrameInlinePage(Page page) {
        logger.info("Setting up the Inline Frames Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Inline Frames Page URL.");
        page.navigate(ConfigurationLoader.get("iFrameUrl"));
    }
    
    public String getBodyContent() {
        logger.info("Returning the content of the left frame.");
        return page.frameLocator(iFrame).locator(textpara).textContent().strip();
    }
}
