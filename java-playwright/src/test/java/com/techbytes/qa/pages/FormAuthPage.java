package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class FormAuthPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(FormAuthPage.class);

    private final String usernameField = "#username";
    private final String passwordField = "#password";
    private final String loginButton = "button[type='submit']";
    private final String messageLocator = "#flash";
    private final String errorLocator = "#flash";

    public FormAuthPage(Page page) {
        logger.info("Setting up the Login Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Login URL.");
        page.navigate(ConfigurationLoader.get("loginurl"));
    }

    public void login(String username, String password) {
        logger.info("Login requested.");
        logger.debug("Entering username.");
        page.fill(usernameField, username);
        logger.debug("Entering password.");
        page.fill(passwordField, password);
        logger.debug("Clicking login button.");
        page.click(loginButton);
    }

    public String getMessage() {
        logger.info("Returning Message.");
        return page.waitForSelector(messageLocator).textContent().strip();
    }

    public boolean isErrorVisible() {
        logger.info("Locator visibility requested.");
        logger.debug("Retrning if the locator is visible.");
        return page.isVisible(errorLocator);
    }
}
