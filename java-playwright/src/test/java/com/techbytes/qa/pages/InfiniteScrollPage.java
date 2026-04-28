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
public class InfiniteScrollPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(InfiniteScrollPage.class);

    public InfiniteScrollPage(Page page) {
        logger.info("Setting up the Infinite Scroll Page.");
        super(page);
    }

    private final String para = ".jscroll-added";

    public void open() {
        logger.info("Navigating to the Infinite Scroll Page URL.");
        page.navigate(ConfigurationLoader.get("infiniteScrollUrl"));
    }
    
    public int getParaCount() {
        logger.info("Returning the count of paras.");
        return page.locator(para).count();
        
    }

    public void scrollDown() {
        logger.info("Scrolling down the page.");
        // Execute JS to scroll to bottom
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");

        // Wait for new content to be added
        logger.debug("waiting for the para to load.");
        page.waitForFunction("() => document.querySelectorAll('.jscroll-added').length > " + getParaCount());
    }
}
