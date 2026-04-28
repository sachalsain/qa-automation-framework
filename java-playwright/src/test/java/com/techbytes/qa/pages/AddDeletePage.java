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
public class AddDeletePage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(AddDeletePage.class);
    
    private final String addButton = "button[onclick='addElement()']";
    private final String deleteButton = "button[onclick='deleteElement()']";
    
    public AddDeletePage(Page page) {
        logger.info("Setting up the Add Delete Page.");
        super(page);
    }

    public void open() {
        logger.info("Navigating to the Add Delete URL.");
        page.navigate(ConfigurationLoader.get("addDeleteurl"));
    }
    
    public boolean isAddButtonVisible() {
        logger.info("Checking if add button is diplayed.");
        return page.isVisible(addButton);
    }
    
    public boolean isDeleteButtonVisible() {
        logger.info("Checking if delete button is diplayed.");
        return page.isVisible(deleteButton);
    }
    
    public void clickAddButton() {
        logger.info("Clicking Add button.");
        page.click(addButton);
    }
    
    public void clickDeleteButton() {
        logger.info("Clicking Delete button.");
        page.click(deleteButton);
    }
}
