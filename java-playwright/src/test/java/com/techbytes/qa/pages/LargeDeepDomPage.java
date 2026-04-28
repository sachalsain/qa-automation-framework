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
public class LargeDeepDomPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(LargeDeepDomPage.class);

    public LargeDeepDomPage(Page page) {
        logger.info("Setting up the Large Deep Dom Page.");
        super(page);
    }

    private final String table = "#large-table";
    
    public void open() {
        logger.info("Navigating to the Large Deep Dom Page URL.");
        page.navigate(ConfigurationLoader.get("largeDeepDomUrl"));
    }
    
    public boolean isTableVisible() {
        logger.info("Returning visibility of the table.");
        return page.locator(table).isVisible();
    }
    
    public String getValueOfCell(int row, int cell) {
        logger.info("Returning the value of required cell.");
        return page.locator(".row-" + row + " .column-" + cell).textContent();
    }
    
}
