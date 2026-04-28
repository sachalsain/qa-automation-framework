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
public class DynamicElementPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DynamicElementPage.class);

    private final String strtBtn = "#start button";
    private final String loading = "#loading";
    private final String helloText = "#finish h4";
    
    public DynamicElementPage(Page page) {
        logger.info("Setting up the Dynamic Element Page.");
        super(page);
    }

    public void open(int pagenum) {
        logger.info("Navigating to the Dynamic Element Page URL.");
        page.navigate(ConfigurationLoader.get("dynamicElementUrl") + pagenum);
    }
    
    public void clickStartBtn() {
        logger.info("Clicking the start button.");
        page.click(strtBtn);
        logger.debug("Waiting for the state of the loading element to change.");
        page.locator(loading).first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }
    
    public boolean isStrtBtnDisplayed() {
        logger.info("Checking start button visibility.");
        return page.isVisible(strtBtn);
    }
    
    public boolean isHelloDisplayed() {
        logger.info("Checking Hello message visibility.");
        return page.isVisible(helloText);
    }
    
}
