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
public class InputsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(InputsPage.class);

    public InputsPage(Page page) {
        logger.info("Setting up the Inputs Page.");
        super(page);
    }

    private final String input = "input[type='number']";

    public void open() {
        logger.info("Navigating to the Infinite Scroll Page URL.");
        page.navigate(ConfigurationLoader.get("inputsUrl"));
    }
    
    public void fillNumber(int number) {
        logger.info("Filling value in the input field.");
        page.locator(input).fill(Integer.toString(number));
    }
    
    public String getValue() {
        logger.info("Filling value in the input field.");
        return page.locator(input).inputValue();
    }
    
}
