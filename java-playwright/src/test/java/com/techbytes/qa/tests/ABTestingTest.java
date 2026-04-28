package com.techbytes.qa.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.ABTestingPage;
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
public class ABTestingTest {

    private static final Logger logger = LoggerFactory.getLogger(ABTestingTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("A/B testing using different browsers")
    @Description("Verify A/B testing using different browsers")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void ABTest() {
        logger.info("A/B Testing using Chrome Browser and Firefox");
        logger.debug("Creating ABTest page instance using Chromium.");

        logger.debug("Creating Playwright object");
        Playwright playwright = Playwright.create();

        logger.debug("Setting BrowserOptions");
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(true);

        logger.debug("Setting Browser for Chromium browser with BrowserOptions");
        Browser chromeBrowser = playwright.chromium().launch(options);
        logger.debug("Setting Browser Context for Chromium browser");
        BrowserContext chromeContext = chromeBrowser.newContext();
        logger.debug("Setting Page for Chromium browser");
        Page chromePage = chromeContext.newPage();

        logger.debug("Creating ABTestingPage instance.");
        ABTestingPage chromeAbTestPage = new ABTestingPage(chromePage);
        logger.debug("Opening ABTest page.");
        chromeAbTestPage.open();
        logger.debug("Retrieving Heading.");
        String chromeHeading = chromeAbTestPage.getHeading();
        logger.debug("Retrieving Paragraph.");
        String chromeParagraph = chromeAbTestPage.getParagraph();

        logger.debug("Setting Browser for Firefox browser with BrowserOptions");
        Browser firefoxBrowser = playwright.chromium().launch(options);
        logger.debug("Setting Browser Context for Firefox browser");
        BrowserContext firefoxContext = firefoxBrowser.newContext();
        logger.debug("Setting Page for Firefox browser");
        Page firefoxPage = firefoxContext.newPage();

        logger.debug("Creating ABTestingPage instance.");
        ABTestingPage firefoxAbTestPage = new ABTestingPage(firefoxPage);
        logger.debug("Opening ABTest page.");
        firefoxAbTestPage.open();
        logger.debug("Retrieving Heading.");
        String firefoxeHeading = firefoxAbTestPage.getHeading();
        logger.debug("Retrieving Paragraph.");
        String firefoxParagraph = firefoxAbTestPage.getParagraph();

        logger.debug("Validating if headings are different.");
        Assert.assertNotSame(chromeHeading, firefoxeHeading);
        
        logger.debug("Validating if paragraphs are same.");
        Assert.assertEquals(chromeParagraph.contains("Also known as split testing"), firefoxParagraph.contains("Also known as split testing"));
        
        
        
        logger.debug("Closing Page.");
        chromePage.close();
        firefoxPage.close();
        logger.debug("Closing Browser Context.");
        chromeContext.close();
        firefoxContext.close();
        logger.debug("Closing Browser.");
        chromeBrowser.close();
        firefoxBrowser.close();
        logger.debug("Closing Playwright.");
        playwright.close();
    }
}
