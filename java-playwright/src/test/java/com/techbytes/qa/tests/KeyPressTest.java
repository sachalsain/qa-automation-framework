package com.techbytes.qa.tests;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.KeyPressPage;
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
public class KeyPressTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(KeyPressTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Key Press Test.")
    @Description("Test to check Key Press work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void KeyPressTest() {
        logger.info("Testing Key Press");
        logger.debug("Creating KeyPressPage instance.");
        KeyPressPage keyPressPage = new KeyPressPage(page);
        logger.debug("Opening Key Press Page.");
        keyPressPage.open();
        logger.debug("Clicking the input field.");
        keyPressPage.clickInput();
        logger.debug("Pressing the key.");
        keyPressPage.pressKey("a");
        logger.debug("checking if the result diplays 'A'.");
        Assert.assertTrue(keyPressPage.getResult().toLowerCase().contains("a"));
        logger.debug("Pressing the 'ArrowDown' key.");
        keyPressPage.pressKey("ArrowDown");
        logger.debug("checking if the result diplays 'down'.");
        Assert.assertTrue(keyPressPage.getResult().toLowerCase().contains("down"));
    }
    
}
