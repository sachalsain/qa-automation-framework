package com.techbytes.qa;

import org.slf4j.LoggerFactory;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.AddDeletePage;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class AddDeleteTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(AddDeleteTest.class);
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Add / Delete Page")
    @Description("Testing Add / Delete Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void addDeleteTest() {
        logger.info(" -> Starting Test of Add / Delete Page");
        logger.debug(" -> Creating AddDeletePage instance.");
        AddDeletePage addDeletePage = new AddDeletePage(getDriver());
        logger.debug(" -> Opening AddDeletePage Page.");
        addDeletePage.open();
        
        logger.debug(" -> Verifying if Add button is visible");
        Assert.assertTrue(addDeletePage.isAddButtonVisible());
        logger.debug(" -> Verified that Add button is visible");
        
        logger.debug(" -> Verifying if Delete button is not visible");
        Assert.assertFalse(addDeletePage.isDeleteButtonVisible());
        logger.debug(" -> Verified that Delete button is not visible");
        
        logger.debug(" -> Click Add Element button");
        addDeletePage.clickAddButton();
        
        logger.debug(" -> Verifying if Delete button is visible");
        Assert.assertTrue(addDeletePage.isDeleteButtonVisible());
        logger.debug(" -> Verified that Delete button is visible");
        
        logger.debug(" -> Click Delete button");
        addDeletePage.clickDeleteButton();
        
        logger.debug(" -> Verifying if Delete button is not visible");
        Assert.assertFalse(addDeletePage.isDeleteButtonVisible());
        logger.debug(" -> Verified that Delete button is not visible");
    }
}
