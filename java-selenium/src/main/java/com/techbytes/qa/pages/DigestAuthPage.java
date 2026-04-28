package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DigestAuthPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DigestAuthPage.class);

    public DigestAuthPage(WebDriver driver) {
        logger.info(" -> Setting up the Digest Authentication Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the Digest Authentication Page...");
        // Register the credentials (admin/admin) before navigation
        ((HasAuthentication) driver).register(() -> new UsernameAndPassword("admin", "admin"));
        openUrl(ConfigurationLoader.get("digestAuthUrl"));
    }
    
    public String getUrl() {
        logger.info(" -> Returning the page URL.");
        return ConfigurationLoader.get("digestAuthUrl");
    }
    
}
