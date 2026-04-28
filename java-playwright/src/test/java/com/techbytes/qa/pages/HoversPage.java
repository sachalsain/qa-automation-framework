package com.techbytes.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class HoversPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HoversPage.class);
    
    public HoversPage(Page page) {
        logger.info("Setting up the Hover Page.");
        super(page);
    }

    private final String pic = "img[alt='User Avatar']";
    private final String userName = ".figcaption h5";

    public void open() {
        logger.info("Navigating to the Hover Page URL.");
        page.navigate(ConfigurationLoader.get("hoversUrl"));
    }
    
    public void hoverOn(int num) {
        logger.info("Hovering on element at index: {}.", num - 1);
        page.locator(pic).nth(num - 1).hover();
    }
    
    public boolean isProperLabelDisplayed(int num) {
        logger.info("Checking if proper Label is displayed.");
        logger.debug("Requesting hover.");
        hoverOn(num);
        logger.debug("Fetching all locators.");
        List<Locator> locators = page.locator(userName).all();
        logger.debug("Returning if proper Label is displayed.");
        return locators.get(num-1).isVisible();
    }
    
}
