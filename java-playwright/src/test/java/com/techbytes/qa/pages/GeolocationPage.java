package com.techbytes.qa.pages;

import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class GeolocationPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationPage.class);

    private final String locationBtn = "button:text('Where am I?')";
    private final String latitude = "#lat-value";
    private final String longitude = "#long-value";
    
    public GeolocationPage(Page page) {
        logger.info("Setting up the Geolocation Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Geolocation Page URL.");
        page.navigate(ConfigurationLoader.get("geoLocationUrl"));
    }
    
    public Map<String, String> getLatLong() {
        logger.info("Fetching Location.");
        logger.debug("Clicking the button to get location.");
        page.click(locationBtn);
        logger.debug("Setting wait to fetch values.");
        page.waitForSelector(latitude);
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("latitude", page.textContent(latitude));
        dictionary.put("longitude", page.textContent(longitude));
        return dictionary;
    }
}
