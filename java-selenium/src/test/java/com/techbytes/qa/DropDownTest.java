package com.techbytes.qa;

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
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Drop Down Page")
    @Description("Testing Drop Down Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void dropDownTest() {
        logger.info(" -> Starting Test of Drop Down Page");
        logger.debug(" -> Creating DropDownPage instance.");
        DropDownPage dropDownPage = new DropDownPage(getDriver());
        logger.debug(" -> Opening Drag Drop Page.");
        dropDownPage.open();
        logger.debug(" -> Verifying if default option is selected.");
        Assert.assertEquals(dropDownPage.getSelectedValue(), "Please select an option", "Default value of 'Please select an option' should be shown.");
        logger.debug(" -> Verified that default option is selected.");
        
        logger.debug(" -> Select Option 2.");
        dropDownPage.selectValue(2);
        
        logger.debug(" -> Verifying if option 2 is now selected.");
        Assert.assertEquals(dropDownPage.getSelectedValue(), "Option 2", "Option 2 should have been selected.");
        logger.debug(" -> Verified that option 2 is now selected.");
    }
}
