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
public class ShiftingContentMenuPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ShiftingContentMenuPage.class);

    public ShiftingContentMenuPage(Page page) {
        logger.info("Setting up the Shifting Content Menu Page.");
        super(page);
    }

    private final String linkClickHere = "click here";
    
    
    public void open() {
        logger.info("Navigating to the Shifting Content Menu Page URL.");
        page.navigate(ConfigurationLoader.get("shiftingContentMenuUrl"));
    }
    
    public String getUrl(int val) {
        logger.info("Clicking the required link");
        switch (val) {
            case 1:
                page.getByText(linkClickHere).nth(val - 1).click();
                break;
            case 2:
                page.getByText(linkClickHere).nth(val - 1).click();
                break;
            case 3:
                page.getByText(linkClickHere).nth(val - 1).click();
                break;
            default:
                throw new AssertionError();
        }
        logger.debug("Returning the page url");
        return page.url();
    }
    
}
