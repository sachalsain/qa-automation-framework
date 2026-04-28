package com.techbytes.qa.tests;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Dynamic Controls Page Test.")
    @Description("Test to check Dynamic Controls work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void dynamicContentTest() {
        logger.info("Testing Dynamic Controls");
        logger.debug("Creating DynamicControlsPage instance.");
        DynamicControlsPage dynamicControlsPage = new DynamicControlsPage(page);
        logger.debug("Opening Dynamic Controls Page.");
        dynamicControlsPage.open();
        logger.debug("Checking if the checkbox is Visible.");
        Assert.assertTrue(dynamicControlsPage.isCheckboxDisplayed(), "The Checkbox must be visisble by default.");
        logger.debug("Clicking the checkbox remove button to remove the checkbox");
        dynamicControlsPage.clickRemoveBtn();
        logger.debug("Checking if the checkbox is Visible.");
        Assert.assertFalse(dynamicControlsPage.isCheckboxDisplayed(), "The Checkbox must be not be visisble after removal.");
        logger.debug("Checking if the checkbox removal success message is displayed.");
        Assert.assertTrue(dynamicControlsPage.isChkBoxSuccessDisplayed(), "The success message 'It's gone!' must be displayed.");
        logger.debug("Clicking the checkbox add button to add the checkbox");
        dynamicControlsPage.clickRemoveBtn();
        logger.debug("Checking if the checkbox is Visible.");
        Assert.assertTrue(dynamicControlsPage.isCheckboxDisplayed(), "The Checkbox must be be visisble after addition.");
        logger.debug("Checking if the checkbox addition success message is displayed.");
        Assert.assertTrue(dynamicControlsPage.isChkBoxSuccessDisplayed(), "The success message 'It's back!' must be displayed.");
        logger.debug("Reloading the page.");
        dynamicControlsPage.reloadPage();
        logger.debug("Checking if the textbox is disabled.");
        Assert.assertFalse(dynamicControlsPage.isTextBoxEnabled(), "The Textbox must be disabled on defualt.");
        logger.debug("Clicking the textbox enable button to enable the textbox");
        dynamicControlsPage.clickEnableBtn();
        logger.debug("Checking if the textbox is enabled.");
        Assert.assertTrue(dynamicControlsPage.isTextBoxEnabled(), "The Textbox must be enabled after clicking enable button.");
        logger.debug("Clicking the textbox disable button to disable the textbox");
        dynamicControlsPage.clickEnableBtn();
        logger.debug("Checking if the textbox is disabled.");
        Assert.assertFalse(dynamicControlsPage.isTextBoxEnabled(), "The Textbox must be disabled after clicking disable button.");
    }
}
