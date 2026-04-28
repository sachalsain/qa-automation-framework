package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DynamicContentPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DynamicContentPage.class);

    public DynamicContentPage(WebDriver driver) {
        logger.info(" -> Setting up the Dynamic Content Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the Dynamic Content Page...");
        openUrl(ConfigurationLoader.get("dynamicContentUrl"));
    }

    public boolean isPageloaded() {
        logger.info(" -> Checking image container element visibility.");
        return driver.findElement(By.cssSelector("#content > .row img")).isDisplayed();
    }

    public List<String> getAllImagesSrc() {
        logger.info(" -> Getting and returning source of all images.");
        List<String> srcList = new ArrayList<>();
        for (WebElement image : driver.findElements(By.cssSelector("#content > .row img"))) {
            String imageSrc = image.getAttribute("src");
            logger.debug("source: {}.", imageSrc);
            if (imageSrc != null && !imageSrc.isEmpty()) {
                srcList.add(imageSrc);
            }
        }
        return srcList;
    }

    public List<String> getAllTexts() {
        logger.info(" -> Getting and returning all texts of the container element.");
        List<String> textList = new ArrayList<>();
        for (WebElement textRow : driver.findElements(By.cssSelector("#content > .row > .large-10"))) {
            String text = textRow.getText();
            logger.debug("text: {}.", text);
            if (text != null && !text.isEmpty()) {
                textList.add(text);
            }
        }
        return textList;
    }

    public void reloadPage() {
        logger.info(" -> Reloading the page.");
        driver.get(driver.getCurrentUrl());
    }
    
}
