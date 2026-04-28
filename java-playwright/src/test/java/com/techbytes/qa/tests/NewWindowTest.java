package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.NewWindowPage;
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
public class NewWindowTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(NewWindowTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("New Window Test.")
    @Description("Test to check New Window Page work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void KeyPressTest() {
        logger.info("Testing New Window");
        logger.debug("Creating NewWindowPage instance.");
        NewWindowPage newWindowPage = new NewWindowPage(page);
        logger.debug("Opening New Window Page.");
        newWindowPage.open();
        logger.debug("Clicking the link to open new Window.");
        newWindowPage.newPage();
        logger.debug("Checking the heading on new page to be 'New Window'.");
        Assert.assertEquals(newWindowPage.getNewPageText(), "New Window", "The text displayed must be 'New Window'");
    }
}
