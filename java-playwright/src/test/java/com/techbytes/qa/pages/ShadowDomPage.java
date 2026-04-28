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
public class ShadowDomPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ShadowDomPage.class);

    public ShadowDomPage(Page page) {
        logger.info("Setting up the Shadow DOM Page.");
        super(page);
    }

    private final String paraText = "span[slot='my-text']";
    private final String listText = "ul[slot='my-text'] li";
    
    
    public void open() {
        logger.info("Navigating to the Shadow DOM Page URL.");
        page.navigate(ConfigurationLoader.get("shadowDomUrl"));
    }
    
    public String getParaText() {
        logger.info("Returning the text displayed in paragraph.");
        return page.locator(paraText).textContent();
    }
    
    public String getLinkText(int itemNum) {
        logger.info("Returning the text displayed in the list of required item.");
        return page.locator(listText).nth(itemNum).textContent();
    }
}
