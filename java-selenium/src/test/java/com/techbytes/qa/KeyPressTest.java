package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.KeyPressPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.Keys;
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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Key Press Page")
    @Description("Testing Key Press Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void keyPressTest() {
        logger.info(" -> Starting Test of Key Press Page");
        logger.debug(" -> Creating KeyPressPage instance.");
        KeyPressPage keyPressPage = new KeyPressPage(getDriver());
        logger.debug(" -> Opening Key Press Page.");
        keyPressPage.open();
        
        logger.debug(" -> Clicking the input field.");
        keyPressPage.clickInput();
        
        logger.debug(" -> Pressing the key.");
        keyPressPage.pressKey(Keys.BACK_SPACE);
        
        logger.debug(" -> Verifying if the result diplays 'BACK_SPACE'.");
        Assert.assertTrue(keyPressPage.getResult().toLowerCase().contains("back_space"), "The result must show 'BACK_SPACE'.");
        logger.debug(" -> Verified that the result diplays 'BACK_SPACE'.");
        
        logger.debug(" -> Pressing the 'ArrowDown' key.");
        keyPressPage.pressKey(Keys.ARROW_DOWN);
        
        logger.debug(" -> Verifying if the result diplays 'down'.");
        Assert.assertTrue(keyPressPage.getResult().toLowerCase().contains("down"), "The reult must display 'down'.");
        logger.debug(" -> Verified that the result diplays 'down'.");
    }
    
}
