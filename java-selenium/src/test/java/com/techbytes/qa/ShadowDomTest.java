package com.techbytes.qa;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Shadow DOM Page")
    @Description("Testing Shadow DOM Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void shadowDomTest() {
        logger.info(" -> Starting Test of Shadow DOM Page");
        logger.debug(" -> Creating ShadowDomPage instance.");
        ShadowDomPage page = new ShadowDomPage(getDriver());
        logger.debug(" -> Opening Shadow DOM Page.");
        page.open();
        
        logger.debug(" -> Verifying if the text in the paragraph matches expected.");
        Assert.assertTrue(page.getParaText().toLowerCase().contains("some different text!"), "The text must contain 'some different text!'");
        logger.debug(" -> Verified that the text in the paragraph matches expected.");
        
        logger.debug(" -> Verifying if the text in the first list item matches expected.");
        Assert.assertTrue(page.getLinkText(0).toLowerCase().contains("some different text!"), "The text must contain 'some different text!'");
        logger.debug(" -> Verified that the text in the first list item matches expected.");
        
        logger.debug(" -> Verifying if the text in the Second list item matches expected.");
        Assert.assertTrue(page.getLinkText(1).toLowerCase().contains("a list!"), "The text must contain 'a list!'");
        logger.debug(" -> Verified that the text in the Second list item matches expected.");
    }
}
