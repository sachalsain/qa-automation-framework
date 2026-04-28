package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.AddDeletePage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class AddDeleteTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AddDeleteTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups="regression")
    @Story("Check Add / Delete Elements")
    @Description("Verify if Add / Delete Elements are working properly")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void addDeleteTest() {
        logger.info("Testing Add / Delete functionality");
        logger.debug("Creating LoginPage instance.");
        AddDeletePage addDeletePage = new AddDeletePage(page);
        logger.debug("Opening Add / Delete Page.");
        addDeletePage.open();
        logger.debug("Verify if Add button is visible");
        Assert.assertTrue(addDeletePage.isAddButtonVisible());
        
        logger.debug("Verify if Delete button is not visible");
        Assert.assertFalse(addDeletePage.isDeleteButtonVisible());
        
        logger.debug("Click Add Element button");
        addDeletePage.clickAddButton();
        
        logger.debug("Verify if Delete button is visible");
        Assert.assertTrue(addDeletePage.isDeleteButtonVisible());
        
        logger.debug("Click Delete button");
        addDeletePage.clickDeleteButton();
        
        logger.debug("Verify if Delete button is not visible");
        Assert.assertFalse(addDeletePage.isDeleteButtonVisible());
    }
}
