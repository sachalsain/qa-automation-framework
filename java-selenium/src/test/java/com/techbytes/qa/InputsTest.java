package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.InputsPage;
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
public class InputsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(InputsTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Inputs Page")
    @Description("Testing Inputs Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void inputsTest() {
        logger.info(" -> Starting Test of Inputs Page");
        logger.debug(" -> Creating InputsPage instance.");
        InputsPage inputsPage = new InputsPage(getDriver());
        logger.debug(" -> Opening Inputs Page.");
        inputsPage.open();
        logger.debug(" -> Filling value 12345.");
        inputsPage.fillNumber(12345);
        
        logger.debug(" -> Verifying if the value is entered correctly.");
        Assert.assertEquals(inputsPage.getValue(), 12345, "The value entered must be 12345");
        logger.debug(" -> Verified that the value is entered correctly.");
    }
    
}
