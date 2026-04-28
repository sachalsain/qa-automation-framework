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
public class KeyPressPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(KeyPressPage.class);

    public KeyPressPage(Page page) {
        logger.info("Setting up the Key Press Page.");
        super(page);
    }

    private final String input = "#target";
    private final String result = "#result";
    
    public void open() {
        logger.info("Navigating to the Key Press Page URL.");
        page.navigate(ConfigurationLoader.get("keyPressesUrl"));
    }
    
    public void clickInput() {
        logger.info("Clicking the input field.");
        page.locator(input).click();
    }
    
    public String getResult() {
        logger.info("Returning the result displayed.");
        return page.locator(result).textContent();
    }
    
    public void pressKey(String key) {
        logger.info("Pressing the required key.");
        page.locator(input).press(key);
    }
    
}
