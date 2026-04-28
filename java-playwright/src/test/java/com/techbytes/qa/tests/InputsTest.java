package com.techbytes.qa.tests;

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

    private static final Logger logger = LoggerFactory.getLogger(InfiniteScrollTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Input Test.")
    @Description("Test to check Input work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void inputsTest() {
        logger.info("Testing Input");
        logger.debug("Creating InputsPage instance.");
        InputsPage inputsPage = new InputsPage(page);
        logger.debug("Opening Input Page.");
        inputsPage.open();
        logger.debug("Filling value 12345.");
        inputsPage.fillNumber(12345);
        logger.debug("checking if the value is entered correctly.");
        Assert.assertEquals(Integer.parseInt(inputsPage.getValue()), 12345, "The value entered must be 12345");
    }
    
}
