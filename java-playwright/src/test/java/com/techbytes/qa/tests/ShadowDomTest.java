package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.ShadowDomPage;
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
public class ShadowDomTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ShadowDomTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Shadow DOM Test.")
    @Description("Test to check Shadow DOM Page work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void shadowDomTest() {
        logger.info("Testing Shadow DOM");
        logger.debug("Creating ShadowDomPage instance.");
        ShadowDomPage shadowDomPage = new ShadowDomPage(page);
        logger.debug("Opening Shadow DOM Page.");
        shadowDomPage.open();
        logger.debug("Checking if the text in the paragraph matches expected.");
        Assert.assertTrue(shadowDomPage.getParaText().toLowerCase().contains("some different text!"), "The text must contain 'some different text!'");
        logger.debug("Checking if the text in the first list item matches expected.");
        Assert.assertTrue(shadowDomPage.getLinkText(0).toLowerCase().contains("some different text!"), "The text must contain 'some different text!'");
        logger.debug("Checking if the text in the Second list item matches expected.");
        Assert.assertTrue(shadowDomPage.getLinkText(1).toLowerCase().contains("a list!"), "The text must contain 'a list!'");
    }
}
