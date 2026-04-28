package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing New Window Page")
    @Description("Testing New Window Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void newWindowTest() {
        logger.info(" -> Starting Test of New Window Page");
        logger.debug(" -> Creating JSAlertsPage instance.");
        NewWindowPage windowPage = new NewWindowPage(getDriver());
        logger.debug(" -> Opening New Window Page.");
        windowPage.open();
        
        logger.debug(" -> Clicking the link to open new Window.");
        windowPage.newPage();
        
        logger.debug(" -> Verifying if the heading on new page is 'New Window'.");
        Assert.assertEquals(windowPage.getNewPageText(), "New Window", "The text displayed must be 'New Window'");
        logger.debug(" -> Verified that the heading on new page is 'New Window'.");
    }
    
}
