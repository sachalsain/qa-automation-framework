package com.techbytes.qa.pages;

import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FloatingMenuPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FloatingMenuPage.class);

    private final String menu = "#menu";
    
    public FloatingMenuPage(Page page) {
        logger.info("Setting up the Floating Menu Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Floating Menu Page URL.");
        page.navigate(ConfigurationLoader.get("floatingMenuUrl"));
    }

    public void scrollDown() {
        logger.info("Scrolling the Floating Menu Page down 1000 pixels.");
        page.mouse().wheel(0, 1000);
    }

    public boolean isMenuVisible() {
        logger.info("Checking if menu is visible");
        return page.locator(menu).isVisible();
    }
    
}
