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
public class DragDropPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DragDropPage.class);
    
    private final String box1 = "#column-a";
    private final String box2 = "#column-b";

    public DragDropPage(Page page) {
        logger.info("Setting up the Drag Drop Page.");
        super(page);
    }
    
    public int open() {
        logger.info("Navigating to the Drag Drop Page URL.");
        return page.navigate(ConfigurationLoader.get("dragDropUrl")).status();
    }
    
    public void performDragDrop() {
        logger.info("Draging box1 to box2.");
        page.locator(box1).dragTo(page.locator(box2));
    }
    
    public String getTextbox1() {
        logger.info("Retrieving inner text of box1.");
        return page.locator(box1).textContent();
    }
    
    public String getTextbox2() {
        logger.info("Retrieving inner text of box2.");
        return page.locator(box2).textContent();
    }
    
}
