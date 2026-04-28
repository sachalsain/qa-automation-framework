package com.techbytes.qa;

import com.techbytes.qa.core.BaseTest;
import com.techbytes.qa.listeners.RetryAnalyzer;
import com.techbytes.qa.pages.GeolocationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class GeolocationTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    @Story("Testing Geolocation Page")
    @Description("Testing Geolocation Page")
    @Severity(SeverityLevel.MINOR)
    @Owner("Tahreem J. Naseem")
    public void geolocationTest() {
        logger.info(" -> Starting Test of Geolocation Page");
        logger.debug(" -> Creating GeolocationPage instance.");
        GeolocationPage geolocationPage = new GeolocationPage(getDriver());
        logger.debug(" -> Opening Geolocation Page.");
        geolocationPage.open();
        logger.debug(" -> Fetching Location.");
        Map<String, Float> location = geolocationPage.getLatLong();
        
        logger.debug(" -> Verifying if latitude matches.");
        Assert.assertTrue(location.get("latitude").intValue() == geolocationPage.getExpectedLat(), "The latitude must match the mocked value");
        logger.debug(" -> Verified that latitude matches.");
        
        logger.debug(" -> Verifying if longitude matches.");
        Assert.assertTrue((location.get("longitude")).intValue() == geolocationPage.getExpectedLong(), "The longitude must match the mocked value");
        logger.debug(" -> Verified that longitude matches.");
    }
    
}
