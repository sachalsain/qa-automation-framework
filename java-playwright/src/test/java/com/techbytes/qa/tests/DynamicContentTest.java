package com.techbytes.qa.tests;


import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.DynamicContentPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DynamicContentTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DynamicContentTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Dynamic Contents are displayed.")
    @Description("Check for Dynamic Contents being displayed each time.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void dynamicContentTest() {
        logger.info("Testing Dynamic Contents");
        logger.debug("Creating DynamicContentPage instance.");
        DynamicContentPage dynamicContentPage = new DynamicContentPage(page);
        logger.debug("Opening Dynamic Content Page.");
        dynamicContentPage.open();
        logger.debug("Getting all the images on first load.");
        List<String> imagesLoad1 = dynamicContentPage.getAllImagesSrc();
        logger.debug("Getting all texts on first load.");
        List<String> textsLoad1 = dynamicContentPage.getAllTexts();
        logger.debug("Reloading Dynamic Content Page.");
        dynamicContentPage.reloadPage();
        logger.debug("Getting all the images on second load.");
        List<String> imagesLoad2 = dynamicContentPage.getAllImagesSrc();
        logger.debug("Getting all texts on second load.");
        List<String> textsLoad2 = dynamicContentPage.getAllTexts();
        logger.debug("Testing if images are different.");
        Assert.assertFalse(imagesLoad1.equals(imagesLoad2), "The images must be replaced on reload");
        logger.debug("Testing if texts are different.");
        Assert.assertFalse(textsLoad1.equals(textsLoad2), "The texts must be replaced on reload");
        
    }
    
}
