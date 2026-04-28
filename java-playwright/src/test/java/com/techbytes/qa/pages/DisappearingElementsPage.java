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
public class DisappearingElementsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DisappearingElementsPage.class);

    public DisappearingElementsPage(Page page) {
        logger.info("Setting up the Disappearing Elements Page.");
        super(page);
    }
    
    private String menu = "#content ul li";

    public void open() {
        logger.info("Navigating to the Disappearing Elements URL.");
        page.navigate(ConfigurationLoader.get("disappearingElementsUrl"));
    }
    
    public boolean isMenuVisible() {
        logger.info("Checking if menu is diplayed.");
        return page.isVisible(menu);
    }
    
    public int countMenuItems() {
        logger.info("Getting count of menu items.");
        return page.locator(menu).all().size();
    }
    
}
