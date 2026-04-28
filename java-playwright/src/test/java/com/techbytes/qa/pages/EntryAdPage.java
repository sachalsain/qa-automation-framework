package com.techbytes.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class EntryAdPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(EntryAdPage.class);

    private final String window = "#modal";
    private final String windowTitle = "#modal .modal .modal-title h3";
    private final String windowText = "#modal .modal .modal-body p";
    private final String windowClose = "#modal .modal .modal-footer p";
    
    public EntryAdPage(Page page) {
        logger.info("Setting up the Entry Ad Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Entry Ad Page URL.");
        page.navigate(ConfigurationLoader.get("entryAdUrl"));
    }
    
    public boolean isWindowVisible() {
        logger.info("Waiting for ad window to be displayed.");
        page.locator(window).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        logger.debug("Returning visibility status.");
        return page.isVisible(window);
    }
    
    public boolean isWindowHidden() {
        logger.info("Returning visibility status.");
        return page.isVisible(window);
    }
    
    public String getWindowTitle() {
        logger.info("Returning Title.");
        return page.locator(windowTitle).textContent();
    }
    
    public String getWindowText() {
        logger.info("Returning Body text.");
        return page.locator(windowText).textContent();
    }
    
    public String getWindowClose() {
        logger.info("Returning Footer text.");
        return page.locator(windowClose).textContent();
    }
    
    public void closeWindow() {
        logger.info("Clicking the close button.");
        page.locator(windowClose).click();
    }
    
    public void reloadPage() {
        logger.info("Reloading page.");
        page.reload();
    }
    
}
