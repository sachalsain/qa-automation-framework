package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.BrokenImagesPage;
import com.techbytes.qa.utilities.ApiUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class BrokenImagesTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BrokenImagesTest.class);
    
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Broken Images Page")
    @Description("Testing Broken Images Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void brokenImagesTest() {
        logger.info(" -> Starting Test of Broken Images Page");
        logger.debug(" -> Creating BrokenImagesPage instance.");
        BrokenImagesPage brokenImagesPage = new BrokenImagesPage(getDriver());
        logger.debug(" -> Opening BrokenImagesPage Page.");
        brokenImagesPage.open();
        
        logger.debug(" -> Getting all the images.");
        List<WebElement> images = brokenImagesPage.getAllImages();
        for (WebElement image : images) {
            String imageSrc = image.getAttribute("src");
            
            logger.debug(" -> Veritying if image source is not null for image: {}", image.getTagName());
            Assert.assertNotNull(imageSrc, "Image src is null for image: " + image.getTagName());
            logger.debug(" -> Veritied that image source is not null for image: {}", image.getTagName());
            logger.debug(" -> Veritying if image source is not blank for image: {}", image.getTagName());
            Assert.assertFalse(imageSrc.isBlank(), "Image src is blank for image: " + image.getTagName());
            logger.debug(" -> Veritied that image source is not blank for image: {}", image.getTagName());
            logger.debug(" -> Veritying if url mentioned in the image source returns 200 for the source: {}", image.getTagName());
            try {
                int statusCode = ApiUtils.getStatusCode(imageSrc);
            logger.debug(" -> Veritied that url mentioned in the image source returns 200 for the source: {}", image.getTagName());
                Assert.assertEquals(statusCode, 200, "Expected 200 status code but got " + statusCode);
            } catch (IOException | InterruptedException ex) {
                logger.error(" -> Request failed to fetch image at: {}. Reason: {}", imageSrc, ex.getMessage());
            }
        }
    }
}
