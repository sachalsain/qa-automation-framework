package com.techbytes.qa.pages;

import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class JSErrorPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(JSAlertsPage.class);

    private List<String> errors = new ArrayList<>();

    public JSErrorPage(Page page) {
        logger.info("Setting up the JavaScripts Error Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the JavaScripts Error Page URL.");
        logger.debug("Registering listener to catch errors.");
        page.onPageError(exception -> {
            errors.add(exception);
        });
        page.navigate(ConfigurationLoader.get("jsErrorUrl"));
    }
    
    public List<String> getErrors() {
        logger.info("Returning Errors.");
        return errors;
    }

}
