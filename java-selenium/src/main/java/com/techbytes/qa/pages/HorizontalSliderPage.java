package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class HorizontalSliderPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(HorizontalSliderPage.class);

    public HorizontalSliderPage(WebDriver driver) {
        logger.info(" -> Setting up the Horizontal Slider Page...");
        super(driver);
    }

    public void open() {
        logger.info(" -> Opening the Horizontal Slider Page...");
        openUrl(ConfigurationLoader.get("horizontalSliderUrl"));
    }
    
    public void setSliderValue(double finalValue) {
        logger.info(" -> Setting the Slider value.");
        logger.debug(" -> Clicking the slider for focus.");
        driver.findElement(By.cssSelector("#content input[type='range']")).click();
        double currentValue = getCurrentValue();
        
        while (currentValue < finalValue) {
            driver.findElement(By.cssSelector("#content input[type='range']")).sendKeys(Keys.ARROW_RIGHT);
            currentValue = getCurrentValue();
        }
        
        while (currentValue > finalValue) {
            driver.findElement(By.cssSelector("#content input[type='range']")).sendKeys(Keys.ARROW_LEFT);
            currentValue = getCurrentValue();
        }
        
    }
    
    public double getCurrentValue() {
        logger.info(" -> Getting and returning the current value.");
        return Double.parseDouble(driver.findElement(By.cssSelector("#range")).getText().trim());
    }
    
}
