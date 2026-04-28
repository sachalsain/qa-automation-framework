package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.DynamicControlsPage;
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
public class DynamicControlsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DynamicControlsTest.class);
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Dynamic Controls Page")
    @Description("Testing Dynamic Controls Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void dynamicControlsTest() {
        logger.info(" -> Starting Test of Dynamic Controls Page");
        logger.debug(" -> Creating DynamicControlsPage instance.");
        DynamicControlsPage dynamicControlsPage = new DynamicControlsPage(getDriver());
        logger.debug(" -> Opening Dynamic Controls Page.");
        dynamicControlsPage.open();
        
        logger.debug(" -> Verifying if the checkbox is Visible by default.");
        Assert.assertTrue(dynamicControlsPage.isCheckboxDisplayed(), "The Checkbox must be visible by default.");
        logger.debug(" -> Verified that the checkbox is Visible by default.");
        
        logger.debug(" -> Clicking the checkbox remove button to remove the checkbox");
        dynamicControlsPage.clickRemoveBtn();
        
        logger.debug(" -> Verifying if the checkbox is Visible after removing.");
        Assert.assertFalse(dynamicControlsPage.isCheckboxDisplayed(), "The Checkbox must be not be visible after removal.");
        logger.debug(" -> Verified that the checkbox is Visible after removing.");
        
        logger.debug(" -> Verifying if the checkbox removal success message is displayed.");
        Assert.assertTrue(dynamicControlsPage.isChkBoxSuccessDisplayed(), "The success message 'It's gone!' must be displayed.");
        logger.debug(" -> Verified that the checkbox removal success message is displayed.");
        
        logger.debug(" -> Clicking the checkbox add button to add the checkbox");
        dynamicControlsPage.clickRemoveBtn();
        
        logger.debug(" -> Verifying if the checkbox is Visible after adding.");
        Assert.assertTrue(dynamicControlsPage.isCheckboxDisplayed(), "The Checkbox must be be visible after addition.");
        logger.debug(" -> Verified that the checkbox is Visible after adding.");
        
        logger.debug(" -> Verifying if the checkbox addition success message is displayed.");
        Assert.assertTrue(dynamicControlsPage.isChkBoxSuccessDisplayed(), "The success message 'It's back!' must be displayed.");
        logger.debug(" -> Verified that the checkbox addition success message is displayed.");
        
        logger.debug(" -> Reloading the page.");
        dynamicControlsPage.reloadPage();
        
        logger.debug(" -> Verifying if the textbox is disabled by default.");
        Assert.assertFalse(dynamicControlsPage.isTextBoxEnabled(), "The Textbox must be disabled on defualt.");
        logger.debug(" -> Verified that the textbox is disabled by default.");
        
        logger.debug(" -> Clicking the textbox enable button to enable the textbox");
        dynamicControlsPage.clickEnableBtn();
        
        logger.debug(" -> Verifying if the textbox is enabled after clicking it.");
        Assert.assertTrue(dynamicControlsPage.isTextBoxEnabled(), "The Textbox must be enabled after clicking enable button.");
        logger.debug(" -> Verified that the textbox is enabled after clicking it.");
        
        logger.debug(" -> Clicking the textbox disable button to disable the textbox");
        dynamicControlsPage.clickEnableBtn();
        
        logger.debug(" -> Verifying if the textbox is disabled after disabling.");
        Assert.assertFalse(dynamicControlsPage.isTextBoxEnabled(), "The Textbox must be disabled after clicking disable button.");
        logger.debug(" -> Verified that the textbox is disabled after disabling.");
    }
    
}
