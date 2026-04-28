package com.techbytes.qa.pages;

import com.techbytes.qa.core.BasePage;
import com.techbytes.qa.core.ConfigurationLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DragDropPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(DragDropPage.class);

    public DragDropPage(WebDriver driver) {
        logger.info(" -> Setting up the Drag Drop Page...");
        super(driver);
    }
    
    public void open() {
        logger.info(" -> Opening the Drag Drop Page...");
        openUrl(ConfigurationLoader.get("dragDropUrl"));
    }
    
    public void performDragDrop() {
        logger.info(" -> Draging box1 to box2.");
        logger.debug(" -> Creating Action.");
        Actions actions = new Actions(driver);
        logger.debug(" -> Performning Drag Drop Action.");
        actions.dragAndDrop(driver.findElement(By.cssSelector("#column-a")), driver.findElement(By.cssSelector("#column-b"))).perform();
    }
    
    public String getTextbox1() {
        logger.info(" -> Getting and returning inner text of box1.");
        return driver.findElement(By.cssSelector("#column-a")).getText();
    }
    
    public String getTextbox2() {
        logger.info(" -> Getting and returning inner text of box2.");
        return driver.findElement(By.cssSelector("#column-b")).getText();
    }
    
}
