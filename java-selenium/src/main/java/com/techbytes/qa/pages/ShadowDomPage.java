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
public class ShadowDomPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ShadowDomPage.class);

    public ShadowDomPage(WebDriver driver) {
        logger.info(" -> Setting up the Shadow DOM Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Shadow DOM Page...");
        openUrl(ConfigurationLoader.get("shadowDomUrl"));
    }
    
    public String getParaText() {
        logger.info(" -> Returning the text displayed in paragraph.");
        return driver.findElement(By.cssSelector("span[slot='my-text']")).getText();
    }
    
    public String getLinkText(int itemNum) {
        logger.info(" -> Returning the text displayed in the list of required item.");
        return driver.findElements(By.cssSelector("ul[slot='my-text'] li")).get(itemNum).getText();
    }
    
}
