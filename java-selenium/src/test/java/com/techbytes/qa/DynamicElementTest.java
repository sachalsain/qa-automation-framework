package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.DynamicElementPage;
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
public class DynamicElementTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DynamicElementTest.class);
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Dynamic Element Page")
    @Description("Testing Dynamic Element Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void dynamicElementTest() {
        logger.info(" -> Starting Test of Dynamic Element Page");
        logger.debug(" -> Creating DynamicElementPage instance.");
        DynamicElementPage dynamicElementPage = new DynamicElementPage(getDriver());
        logger.debug(" -> Opening Dynamic Element Page.");
        dynamicElementPage.open(1);
        
        logger.debug(" -> Verifying if the start button is displayed on default.");
        Assert.assertTrue(dynamicElementPage.isStrtBtnDisplayed(), "The start button be visible on default");
        logger.debug(" -> Verified that the start button is displayed on default.");
        
        logger.debug(" -> Verifying if the Hello text is hidden.");
        Assert.assertFalse(dynamicElementPage.isHelloDisplayed(), "The text must be hidden on default");
        logger.debug(" -> Verified that the Hello text is hidden.");
        
        logger.debug(" -> Clicking the start button.");
        dynamicElementPage.clickStartBtn();
        
        logger.debug(" -> Verifying if the start button is hidden after click.");
        Assert.assertFalse(dynamicElementPage.isStrtBtnDisplayed(), "The start button must be hidden");
        logger.debug(" -> Verified that the start button is hidden after click.");
        
        logger.debug(" -> Verifying if the Hello text is visible after start button click.");
        Assert.assertTrue(dynamicElementPage.isHelloDisplayed(), "The text must be visible");
        logger.debug(" -> Verified that the Hello text is visible after start button click.");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Inserting Element Page Test.")
    @Description("Test to check Inserting Element work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void insertElementTest() {
        logger.info(" -> Testing Inserting Element");
        logger.debug(" -> Creating DynamicElementPage instance.");
        DynamicElementPage dynamicElementPage = new DynamicElementPage(getDriver());
        logger.debug(" -> Opening Dynamic Element Page.");
        dynamicElementPage.open(2);
        
        logger.debug(" -> Verifying if the start button is displayed on default.");
        Assert.assertTrue(dynamicElementPage.isStrtBtnAvailable(), "The start button be visible on default");
        logger.debug(" -> Verified that the start button is displayed on default.");
        
        logger.debug(" -> Verifying if the Hello text is hidden.");
        Assert.assertFalse(dynamicElementPage.isHelloAvailable(), "The text must be hidden on default");
        logger.debug(" -> Verified that the Hello text is hidden.");
        
        logger.debug(" -> Clicking the start button.");
        dynamicElementPage.clickStartBtn();
        
        logger.debug(" -> Verifying if the start button is hidden after click.");
        Assert.assertFalse(dynamicElementPage.isStrtBtnDisplayed(), "The start button be hidden");
        logger.debug(" -> Verified that the start button is hidden after click.");
        
        logger.debug(" -> Verifying if the Hello text is visible after start button click.");
        Assert.assertTrue(dynamicElementPage.isHelloAvailable(), "The text must be visible");
        logger.debug(" -> Verified that the Hello text is visible after start button click.");
    }
    
}
