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
public class DropDownPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DropDownPage.class);
    
    private final String dropDownElement = "#dropdown";

    public DropDownPage(Page page) {
        logger.info("Setting up the Dropdown Page.");
        super(page);
    }
    
    public int open() {
        logger.info("Navigating to the Dropdown Page URL.");
        return page.navigate(ConfigurationLoader.get("dropDownUrl")).status();
    }
    
    public void selectValue(int value) {
        logger.info("Setting value for dropdown element to {}.", value);
        page.locator(dropDownElement).selectOption(String.valueOf(value));
    }
    
    public String getSelectedValue() {
        logger.info("Returning the selected value of the dropdown element.");
        return page.locator(dropDownElement).locator("option:checked").textContent();
    }
}
