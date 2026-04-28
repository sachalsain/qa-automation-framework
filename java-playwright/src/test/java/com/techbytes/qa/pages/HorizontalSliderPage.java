package com.techbytes.qa.pages;

import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class HorizontalSliderPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HorizontalSliderPage.class);
    
    public HorizontalSliderPage(Page page) {
        logger.info("Setting up the Horizontal Slider Page.");
        super(page);
    }

    private final String slider = "#content input[type='range']";
    private final String sliderValue = "#range";

    public void open() {
        logger.info("Navigating to the Geolocation Page URL.");
        page.navigate(ConfigurationLoader.get("horizontalSliderUrl"));
    }
    
    public void setSliderValue(double finalValue) {
        logger.info("Setting the Slider value.");
        logger.debug("Clicking the slider for focus.");
        page.locator(slider).click();
        double currentValue = getCurrentValue();
        
        while (currentValue < finalValue) {
            page.locator(slider).press("ArrowRight");
            currentValue = getCurrentValue();
        }
        
        while (currentValue > finalValue) {
            page.locator(slider).press("ArrowLeft");
            currentValue = getCurrentValue();
        }
        
    }
    
    public double getCurrentValue() {
        return Double.parseDouble(page.locator(sliderValue).textContent());
    }
    
}
