package com.techbytes.qa;

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
public class CheckBoxTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CheckBoxTest.class);
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Checkbox Page")
    @Description("Testing Checkbox Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void checkboxTest() {
        logger.info(" -> Starting Test of Checkbox Page");
        logger.debug(" -> Creating CheckBoxPage instance.");
        CheckBoxPage checkBoxPage = new CheckBoxPage(getDriver());
        logger.debug("O -> pening CheckBoxPage Page.");
        checkBoxPage.open();
        logger.debug(" -> Verifying if 2 checkboxes are present on the page.");
        int actualValue = checkBoxPage.getCheckboxCount();
        logger.debug(" -> Verifying if 2 checkboxes are present on the page.");
        Assert.assertEquals(actualValue, 2, "Page should have 2 checkboxes but found {" + actualValue + "} checkboxes.");
        logger.debug(" -> Verified that 2 checkboxes are present on the page.");
        
        logger.debug(" -> Verifying if the first checkbox is not selected.");
        Assert.assertFalse(checkBoxPage.getCheckbox(0).isSelected(), "The first checkbox should not be selected.");
        logger.debug(" -> Verified that the first checkbox is not selected.");
        
        logger.debug(" -> Verifying if the second checkbox is selected.");
        Assert.assertTrue(checkBoxPage.getCheckbox(1).isSelected(), "The second checkbox should be selected.");
        logger.debug(" -> Verified that the second checkbox is selected.");
        
        logger.debug(" -> Selecting that first checkbox.");
        checkBoxPage.selectCheckbox(0);
        
        logger.debug(" -> Verifying if the first checkbox is selected now.");
        Assert.assertTrue(checkBoxPage.getCheckbox(0).isSelected(), "The first checkbox should now be selected");
        logger.debug(" -> Verified that the first checkbox is selected now.");
    }
}
