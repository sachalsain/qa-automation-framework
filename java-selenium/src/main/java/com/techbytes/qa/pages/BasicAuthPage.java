package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class BasicAuthPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasicAuthPage.class);

    public BasicAuthPage(WebDriver driver) {
        logger.info(" -> Setting up the Basic Authentication Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the Basic Authentication Page...");
        openUrl(ConfigurationLoader.get("basicAuthUrl"));
    }
    
    public String getParagraphText() {
        logger.info(" -> Returning content text after visiblity wait.");
        return wait.waitForVisible(By.cssSelector("p")).getText();
    }
    
    public String getUrl() {
        logger.info(" -> Returning page URL.");
        return ConfigurationLoader.get("basicAuthUrl");
    }
    
}
