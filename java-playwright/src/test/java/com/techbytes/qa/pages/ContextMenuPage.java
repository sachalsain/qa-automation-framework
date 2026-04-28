package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class ContextMenuPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ContextMenuPage.class);

    public ContextMenuPage(Page page) {
        logger.info("Setting up the ContextMenu Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the ContextMenu Page URL.");
        page.navigate(ConfigurationLoader.get("contextMenuUrl"));
    }
    
    private Locator contextMenu = page.locator(("#hot-spot"));
    
    public String getTextOfAlert() {
        logger.info("Returning text shown on alert.");
        logger.debug("Creating a dialog listener to capture the message.");
        final String[] message = {null};
        page.onDialog((dialog) -> {
            message[0] = dialog.message();
            dialog.accept();
        });
        logger.debug("Right clicking to activate alert.");
        contextMenu.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        return message[0];
    }
}
