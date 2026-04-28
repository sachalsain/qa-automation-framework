package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DigestAuthPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DigestAuthPage.class);
    
    private final String headingSelector = "h3";
    private final String paragraphSelector = "p";

    public DigestAuthPage(Page page) {
        logger.info("Setting up the Digest Auth Page.");
        super(page);
    }
    
    public int open() {
        logger.info("Navigating to the Digest Auth URL.");
        return page.navigate(ConfigurationLoader.get("digestAuthUrl")).status();
    }
    
    public String getUrl() {
        logger.info("URL requested.");
        return ConfigurationLoader.get("digestAuthUrl");
    }
    
    public String getHeading() {
        logger.info("Heading requested.");
        page.waitForSelector(headingSelector);
        logger.debug("Retrning heading.");
        return page.textContent(headingSelector);
    }
    
    public String getParagraph() {
        logger.info("Paragraph requested.");
        page.waitForSelector(paragraphSelector);
        logger.debug("Retrning paragraph.");
        return page.textContent(paragraphSelector);
    }
}
