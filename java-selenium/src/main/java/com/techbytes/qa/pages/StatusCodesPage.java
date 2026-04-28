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
public class StatusCodesPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(StatusCodesPage.class);

    public StatusCodesPage(WebDriver driver) {
        logger.info(" -> Setting up the Status Codes Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Status Codes Page...");
        openUrl(ConfigurationLoader.get("statusCodesUrl"));
    }

    public String getBaseUrl() {
        logger.info(" -> Returning the base URL of the page...");
        return ConfigurationLoader.get("statusCodesUrl");
    }
    
}
