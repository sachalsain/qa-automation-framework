package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.CheckBoxPage;
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
public class CheckboxTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CheckboxTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Checkbox Page")
    @Description("Testing Checkbox Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void CheckBoxTest() {
        logger.info("Testing Checkbox Page");
        logger.debug("Creating CheckBoxPage instance.");
        CheckBoxPage checkBoxPage = new CheckBoxPage(page);
        logger.debug("Opening CheckBoxPage Page.");
        checkBoxPage.open();
        logger.debug("Checking count of checkboxes.");
        Assert.assertEquals(checkBoxPage.getCheckboxCount(), 2, "There should be 2 checkboxes");
        logger.debug("Checking that first checkbox should not be selected.");
        Assert.assertFalse(checkBoxPage.getCheckbox(0).isChecked(), "The first checkbox should not be selected");
        logger.debug("Checking that second checkbox should be selected.");
        Assert.assertTrue(checkBoxPage.getCheckbox(1).isChecked(), "The second checkbox should be selected");
        logger.debug("Selecting that first checkbox and checking if it is selected now.");
        checkBoxPage.selectCheckbox(0);
        Assert.assertTrue(checkBoxPage.getCheckbox(0).isChecked(), "The first checkbox should now be selected");
    }
}
