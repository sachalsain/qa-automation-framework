package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.DropDownPage;
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
public class DropDownTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DropDownTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups="regression")
    @Story("Check Dropdown Elements")
    @Description("Verify if Dropdown Elements are working properly")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void dropdownTest() {
        logger.info("Testing Dropdown functionality");
        logger.debug("Creating DropdownPage instance.");
        DropDownPage dropDownPage = new DropDownPage(page);
        logger.debug("Opening Dropdown Page.");
        dropDownPage.open();
        logger.debug("Verify if default option is selected.");
        Assert.assertEquals(dropDownPage.getSelectedValue(), "Please select an option", "Default value of 'Please select an option' should be shown.");
        logger.debug("Select Option 2.");
        dropDownPage.selectValue(2);
        logger.debug("Verify if option 2 is now selected.");
        Assert.assertEquals(dropDownPage.getSelectedValue(), "Option 2", "Option 2 should have been selected.");
    }
}
