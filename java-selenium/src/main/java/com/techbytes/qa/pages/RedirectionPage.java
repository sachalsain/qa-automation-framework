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
public class RedirectionPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(RedirectionPage.class);

    public RedirectionPage(WebDriver driver) {
        logger.info(" -> Setting up the Redirection Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Redirection Page...");
        openUrl(ConfigurationLoader.get("redirectionUrl"));
    }
    
    public void clickLink() {
        logger.info(" -> Clicking the Redirection link.");
        driver.findElement(By.cssSelector("#redirect")).click();
    }
    
    public String getRedirectedUrl() {
        logger.info(" -> Returning the Redirected Url.");
        return driver.getCurrentUrl();
    }
    
    public String getredirectedHeading() {
        logger.info(" -> Returning the Expected Url.");
        return driver.findElement(By.cssSelector("#content .example h3")).getText();
    }
    
}
