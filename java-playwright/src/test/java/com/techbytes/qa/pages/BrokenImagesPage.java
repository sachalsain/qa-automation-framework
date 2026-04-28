package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
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
public class BrokenImagesPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BrokenImagesPage.class);
    
    private final String imageContainer = ".example img";

    public BrokenImagesPage(Page page) {
        logger.info("Setting up the Broken Images Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Broken Images URL.");
        page.navigate(ConfigurationLoader.get("brokenImagesUrl"));
    }

    public boolean isPageloaded() {
        logger.info("Checking Page visibility.");
        return page.isVisible(imageContainer);
    }

    public List<Locator> getAllImages() {
        logger.info("Getting all images.");
        return page.locator(imageContainer).all();
    }
    
    public APIResponse getAPIResponse(APIRequestContext requestContext, String URL, String imageSrc) {
        return requestContext.head(URL + imageSrc);
    }
}
