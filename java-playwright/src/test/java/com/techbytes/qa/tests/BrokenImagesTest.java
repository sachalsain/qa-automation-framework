package com.techbytes.qa.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.core.ConfigurationLoader;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.BrokenImagesPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class BrokenImagesTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BrokenImagesTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Images are displayed.")
    @Description("Check for broken links to images.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void brokenImageTest() {
        logger.info("Testing Broken links of images");
        logger.debug("Creating BrokenImagesPage instance.");
        BrokenImagesPage brokenImagesPage = new BrokenImagesPage(page);
        logger.debug("Opening Images Page.");
        brokenImagesPage.open();
        logger.debug("Getting all the images.");
        List<Locator> images = brokenImagesPage.getAllImages();
        logger.debug("Checking each image if displayed properly.");
        for (Locator image : images) {
            //  OPTION 1: Check if the image is loaded in the browser
//            image.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//
//            boolean isImageLoaded = (boolean) image.evaluate("img => { return img.complete && img.naturalWidth > 0; }", image.elementHandle());
//            Assert.assertTrue(isImageLoaded, "The image must be displayed.");

//              OPTION 2: Check if the image URL return 200 OK status with an APIRequest
            String imageSrc = image.getAttribute("src");
            try {
                PlaywrightAssertions.assertThat(brokenImagesPage.getAPIResponse(getNewContext(), ConfigurationLoader.get("baseurl") + "/", imageSrc)).isOK();
            } catch (Exception e) {
                logger.error("BROKEN IMAGE (API check failed): " + imageSrc + " - " + e.getMessage());
            }
        }
    }
}
