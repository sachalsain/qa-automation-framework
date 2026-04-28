package com.techbytes.qa.core;

import com.microsoft.playwright.Page;
import java.lang.reflect.InvocationTargetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
/**
 * Responsible for default methods for all pages
 */
public class BasePage {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    protected Page page;

    public BasePage(Page page) {
        logger.info("Setting up the Page object");
        this.page = page;
    }

    public void openUrl(String url) {
        logger.info("Navigating to requested URL.");
        page.navigate(url);
    }

    public <T extends BasePage> T createPage(Class<T> pageClass) {
        logger.info("Creating instance of {}.", pageClass.getSimpleName());
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            logger.error("Error creating instance of {}.", pageClass.getSimpleName());
            throw new RuntimeException("Unable to create instance of the page class [" + pageClass.getSimpleName() + "]", ex);
        }
    }
}
