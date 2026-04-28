package com.techbytes.qa.pages;

import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ForgotPasswordPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordPage.class);

    private final String emailInput = "#email";
    private final String submitBtn = "#form_submit[type='submit']";
    private final String message = "h1";
    
    public ForgotPasswordPage(Page page) {
        logger.info("Setting up the Forgot Password Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Forgot Password Page URL.");
        page.navigate(ConfigurationLoader.get("forgotPasswordUrl"));
    }

    public void submitForm(String email) {
        logger.info("Entering the provided email and submitting form.");
        logger.debug("Filling the email.");
        page.fill(emailInput, email);
        logger.debug("Clicking the submit button.");
        page.click(submitBtn);
    }

    public String getMessage() {
        logger.info("retrieving message.");
        return page.waitForSelector(message).textContent();
    }
    
    
}
