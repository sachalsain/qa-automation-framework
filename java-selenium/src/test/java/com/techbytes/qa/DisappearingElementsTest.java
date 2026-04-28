package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.DisappearingElementsPage;
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
public class DisappearingElementsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DisappearingElementsTest.class);
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Disappearing Elements Page")
    @Description("Testing Disappearing Elements Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void disappearingElementsTest() {
        logger.info(" -> Starting Test of Disappearing Elements Page");
        logger.debug(" -> Creating DisappearingElementsPage instance.");
        DisappearingElementsPage dElemPage = new DisappearingElementsPage(getDriver());
        logger.debug(" -> Opening Disappearing Elements Page.");
        dElemPage.open();
        
        logger.debug(" -> Verifying if menu is displayed.");
        Assert.assertTrue(dElemPage.isMenuVisible(), "The menu must be displayed.");
        logger.debug(" -> Verified that menu is displayed.");
        
        logger.debug(" -> Verifying if menu displays 4 or 5 items.");
        Assert.assertTrue(dElemPage.countMenuItems() >= 4, "The menu items must display more than or equal to 4 items.");
        logger.debug(" -> Verified that menu displays 4 or 5 items.");
    }
}
