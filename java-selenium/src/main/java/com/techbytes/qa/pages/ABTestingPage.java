package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ABTestingPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ABTestingPage.class);

    public ABTestingPage(WebDriver driver) {
        logger.info(" -> Setting up the A/B Testing Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the A/B Testing Page...");
        openUrl(ConfigurationLoader.get("abtesturl"));
    }
    
}
