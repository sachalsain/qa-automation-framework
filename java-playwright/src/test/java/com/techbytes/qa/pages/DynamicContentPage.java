package com.techbytes.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DynamicContentPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DynamicContentPage.class);

    private final String imageContainer = "#content > .row img";
    private final String textContainer = "#content > .row > .large-10";

    public DynamicContentPage(Page page) {
        logger.info("Setting up the Dynamic Content Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Dynamic Content Page URL.");
        page.navigate(ConfigurationLoader.get("dynamicContentUrl"));
    }

    public boolean isPageloaded() {
        logger.info("Checking Page visibility.");
        return page.isVisible(imageContainer);
    }

    public List<String> getAllImagesSrc() {
        logger.info("Getting source of all images.");
        List<String> srcList = new ArrayList<>();
        for (Locator image : page.locator(imageContainer).all()) {
            String imageSrc = image.getAttribute("src");
            logger.debug("source: {}.", imageSrc);
            if (imageSrc != null && !imageSrc.isEmpty()) {
                srcList.add(imageSrc);
            }
        }
        return srcList;
    }

    public List<String> getAllTexts() {
        logger.info("Getting all texts.");
        List<String> textList = new ArrayList<>();
        for (Locator textRow : page.locator(textContainer).all()) {
            String text = textRow.textContent();
            logger.debug("text: {}.", text);
            if (text != null && !text.isEmpty()) {
                textList.add(text);
            }
        }
        return textList;
    }

    public void reloadPage() {
        logger.info("Reloading the page.");
        page.reload();
    }

}
