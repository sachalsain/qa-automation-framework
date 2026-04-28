package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.utilities.WaitUtilities;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class GeolocationPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationPage.class);

    public GeolocationPage(WebDriver driver) {
        logger.info(" -> Setting up the Geolocation Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Geolocation Page...");
        openUrl(ConfigurationLoader.get("geoLocationUrl"));
    }
    
    public Map<String, Float> getLatLong() {
        logger.info(" -> Fetching Location.");
        logger.debug(" -> Clicking the button to get location.");
        driver.findElement(By.xpath("//button[contains(text(), 'Where am I?')]")).click();
        logger.debug(" -> Setting wait to fetch values.");
        logger.debug(" -> Initializing the wait for the state of the element of window to change.");
        WaitUtilities waitUtility = new WaitUtilities(driver);
        logger.debug(" -> Waiting for the state of the loading element of window to change.");
        waitUtility.waitForVisible(By.cssSelector("#lat-value"));
        Map<String, Float> dictionary = new HashMap<>();
        dictionary.put("latitude", Float.valueOf(driver.findElement(By.cssSelector("#lat-value")).getText().trim()));
        dictionary.put("longitude", Float.valueOf(driver.findElement(By.cssSelector("#long-value")).getText().trim()));
        return dictionary;
    }

    public int getExpectedLat() {
        logger.info(" -> Returning expected latitude value...");
        return Integer.parseInt(ConfigurationLoader.get("locLat"));
    }

    public int getExpectedLong() {
        logger.info(" -> Returning expected logitude value...");
        return Integer.parseInt(ConfigurationLoader.get("locLong"));
    }
    
    
    
}
