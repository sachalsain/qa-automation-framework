package com.techbytes.qa.tests;

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

    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "regression")
    @Story("Geolocation Page Test.")
    @Description("Test to check Geolocation work as expected.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Tahreem J. Naseem")
    public void geolocationTest() {
        logger.info("Testing Geolocation");
        logger.debug("Setting up Geolocation Playwright instance.");
        setupForGeolocation();
        logger.debug("Creating GeolocationPage instance.");
        GeolocationPage geolocationPage = new GeolocationPage(page);
        logger.debug("Opening Geolocation Page.");
        geolocationPage.open();
        logger.debug("Fetching Location.");
        Map<String, String> location = geolocationPage.getLatLong();
        logger.debug("Checking if latitude matches.");
        Assert.assertTrue(location.get("latitude").equals("31.366392398633323"), "The latitude must match the mocked value");
        logger.debug("Checking if longitude matches.");
        Assert.assertTrue(location.get("longitude").equals("74.25476741254472"), "The longitude must match the mocked value");
    }
}
