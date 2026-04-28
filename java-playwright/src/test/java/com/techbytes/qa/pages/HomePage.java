package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class HomePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    public HomePage(Page page) {
        logger.info("Setting up the Home Page.");
        super(page);
    }

    private final String favicon = "link[rel ~= 'icon']";
    private final String linkContainer = "ul li";

    public void open(String url) {
        logger.info("Navigating to Home Page.");
        openUrl(ConfigurationLoader.get("baseurl"));
    }

    public List<Locator> getAllLinks() {
        logger.info("Extracting Links.");
        return page.locator(linkContainer).all();
    }

    public Locator getFavicon() {
        logger.info("Returning favicon if found.");
        return page.locator(favicon);
    }
}
