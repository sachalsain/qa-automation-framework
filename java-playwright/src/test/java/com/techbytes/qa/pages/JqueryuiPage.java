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
public class JqueryuiPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(JqueryuiPage.class);

    public JqueryuiPage(Page page) {
        logger.info("Setting up the Jqueryui Page.");
        super(page);
    }

    private final String mnuDisabled = "text=Disabled";
    private final String mnuEnabled = "text=Enabled";
    private final String mnuDownloads = "text=Downloads";
    private final String mnuCSV = "text=CSV";
    
    public void open() {
        logger.info("Navigating to the Jqueryui Page URL.");
        page.navigate(ConfigurationLoader.get("jqueryuiUrl"));
    }
    
    public boolean checkDisabled() {
        logger.info("Checking if the menu Item is disabled.");
        return page.locator(mnuDisabled).isDisabled();
    }
    
    public boolean checkEnabled() {
        logger.info("Checking if the menu Item is enabled.");
        return page.locator(mnuEnabled).isEnabled();
    }
    
    public void hoverEnabled() {
        logger.info("Hovering over Enabled Menu Item.");
        page.locator(mnuEnabled).hover();
        page.locator(mnuDownloads).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }
    
    public boolean isDownloadsVisible() {
        logger.info("Returning visibility of downloads menu item");
        return page.locator(mnuDownloads).isVisible();
    }
    
    public void hoverDownloads() {
        logger.info("Hovering over Downloads Menu Item.");
        page.locator(mnuDownloads).hover();
        page.locator(mnuCSV).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }
    
    public boolean isCSVVisible() {
        logger.info("Returning visibility of CSV menu item");
        return page.locator(mnuCSV).isVisible();
    }
}
