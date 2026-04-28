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
public class NewWindowPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(NewWindowPage.class);

    public NewWindowPage(Page page) {
        logger.info("Setting up the New Window Page.");
        super(page);
    }

    private final String newWindowLink = "Click Here";
    private final String newWindowtext = "h3";
    private Page popup;
    
    public void open() {
        logger.info("Navigating to the New Window Page URL.");
        page.navigate(ConfigurationLoader.get("newWindowUrl"));
    }
    
    public void newPage() {
        logger.info("Clicking the new page link.");
        popup = page.waitForPopup(() -> {
            page.getByText(newWindowLink).click();
        });
    }
    
    public String getNewPageText() {
        logger.info("Returning the text displayed on new page.");
        popup.waitForLoadState();
        return popup.locator(newWindowtext).textContent();
    }
    
}
