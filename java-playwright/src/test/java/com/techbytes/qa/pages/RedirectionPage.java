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
public class RedirectionPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(RedirectionPage.class);

    public RedirectionPage(Page page) {
        logger.info("Setting up the Redirection Page.");
        super(page);
    }

    private final String link = "#redirect";
    private final String expectedUrl = "https://the-internet.herokuapp.com/status_codes";
    private final String heading = "#content .example h3";
    
    public void open() {
        logger.info("Navigating to the Redirection Page URL.");
        page.navigate(ConfigurationLoader.get("redirectionUrl"));
    }
    
    public void clickLink() {
        logger.info("Clicking the Redirection link.");
        page.locator(link).click();
    }
    
    public String getRedirectedUrl() {
        logger.info("Returning the Redirected Url.");
        return page.url();
    }
    
    public String getredirectedHeading() {
        logger.info("Returning the Expected Url.");
        return page.locator(heading).textContent();
    }
    
}
