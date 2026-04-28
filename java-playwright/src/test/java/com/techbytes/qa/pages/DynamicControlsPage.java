package com.techbytes.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DynamicControlsPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DynamicControlsPage.class);

    private final String chkBox = "#checkbox-example #checkbox";
    private final String chkBoxBtn = "#checkbox-example button[type='button']";
    private final String chkBoxMsg = "#checkbox-example #message";
    private final String chkBoxLoading = "#checkbox-example #loading";
    private final String textBox = "#input-example input[type='text']";
    private final String textBoxBtn = "#input-example button[type='button']";
    private final String textBoxMsg = "#input-example #message";
    private final String textBoxLoading = "#input-example #loading";
    
    public DynamicControlsPage(Page page) {
        logger.info("Setting up the Dynamic Controls Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Dynamic Content Page URL.");
        page.navigate(ConfigurationLoader.get("dynamicControlsUrl"));
    }
    
    public void reloadPage() {
        logger.info("Reloading the Dynamic Content Page URL.");
        page.reload();
    }
    
    public boolean isCheckboxDisplayed() {
        logger.info("Checking if checkbox is displayed.");
        return page.isVisible(chkBox);
    }
    
    public void clickRemoveBtn() {
        logger.info("Clicking the remove button.");
        page.click(chkBoxBtn);
        logger.debug("Waiting for the state of the loading element to change.");
        page.locator(chkBoxLoading).first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }
    
    public boolean isChkBoxSuccessDisplayed() {
        logger.info("Checking success message on checkbox removal.");
        return page.isVisible(chkBoxMsg);
    }
    
    public boolean isTextBoxEnabled() {
        logger.info("Cheking if textkbox is enabled.");
        return page.locator(textBox).isEnabled();
    }
    
    public void clickEnableBtn() {
        logger.info("Clicking the Disable button.");
        page.click(textBoxBtn);
        logger.debug("Waiting for the state of the loading element to change.");
        page.locator(textBoxLoading).first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }
    
    public boolean isTextBoxSuccessDisplayed() {
        logger.info("Checking success message on Textbox disabling.");
        return page.isVisible(textBoxMsg);
    }
    
}
