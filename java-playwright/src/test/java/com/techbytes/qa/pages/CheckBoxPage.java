package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class CheckBoxPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckBoxPage.class);

    public CheckBoxPage(Page page) {
        logger.info("Setting up the Checkbox Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Checkbox Page URL.");
        page.navigate(ConfigurationLoader.get("checkboxurl"));
    }
    
    private Locator checkboxes = page.locator(("#checkboxes > input[type='checkbox']"));
    
    public int getCheckboxCount() {
        logger.info("Returning count of checkboxes.");
        return checkboxes.count();
    }
    
    public Locator getCheckbox(int number) {
        logger.info("Returning check box number {}.", number);
        return checkboxes.nth(number);
    }
    
    public void selectCheckbox(int number) {
        logger.info("Selecting check box number {}.", number);
        checkboxes.nth(number).check();
    }
    
}
