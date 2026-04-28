package com.techbytes.qa.pages;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class JSAlertsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(JSAlertsPage.class);

    public JSAlertsPage(Page page) {
        logger.info("Setting up the JavaScripts Alerts Page.");
        super(page);
    }

    private final String btnAlert = "text=Click for JS Alert";
    private final String btnConfirm = "text=Click for JS Confirm";
    private final String btnPrompt = "text=Click for JS Prompt";
    private final String result = "#result";
    private String message;

    public void open() {
        logger.info("Navigating to the JavaScripts Alerts Page URL.");
        page.navigate(ConfigurationLoader.get("jsAlertsUrl"));
    }

    Consumer<Dialog> dialogOkHandler = dialog -> {
        message = dialog.message();
        if (dialog.type().equals("prompt")) {
            dialog.accept(ConfigurationLoader.get("promptText"));
        } else {
            dialog.accept();
        }
    };

    Consumer<Dialog> dialogCancelHandler = dialog -> {
        message = dialog.message();
        dialog.dismiss();
    };

    public String clickAlertBtn() {
        logger.info("Clicking the Alert button.");
        page.onDialog(dialogOkHandler);
        page.locator(btnAlert).click();
        page.offDialog(dialogOkHandler);
        return message;
    }

    public String clickConfirmBtn(String value) {
        logger.info("Clicking the Confirm button.");
        if (value.equalsIgnoreCase("ok")) {
            page.onDialog(dialogOkHandler);
            page.locator(btnConfirm).click();
            page.offDialog(dialogOkHandler);
        } else {
            page.onDialog(dialogCancelHandler);
            page.locator(btnConfirm).click();
            page.offDialog(dialogCancelHandler);
        }
        return message;
    }

    public String clickPromptBtn(String value) {
        logger.info("Clicking the Prompt button.");
        if (value.equalsIgnoreCase("ok")) {
            page.onDialog(dialogOkHandler);
            page.locator(btnPrompt).click();
            page.offDialog(dialogOkHandler);
        } else {
            page.onDialog(dialogCancelHandler);
            page.locator(btnPrompt).click();
            page.offDialog(dialogCancelHandler);
        }
        return message;
    }

    public String getResult() {
        logger.info("Returning the Result.");
        return page.locator(result).textContent();
    }
}
