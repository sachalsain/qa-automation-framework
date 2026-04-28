package com.techbytes.qa.core;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */

/**
 * Base test class containing setup and teardown logic for all tests.
 */
public class BaseTest {

    protected Page page;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true, groups = {"smoke", "regression"})
    public void setup() {

        String browser = ConfigurationLoader.get("browser");
        logger.info("Starting test on browser: {}", browser);

        logger.debug("Requesting Factory to initialize Playwright");
        PlaywrightFactory.initBrowser(browser);
        logger.debug("Requesting Factory for page object");
        page = PlaywrightFactory.getPage();
    }
    
    public void setupWithAuth(String username, String password) {
        logger.info("Context Setup with Auth requested.");
        logger.debug("Clearing the default non-Auth instance.");
        teardown();
        String browser = ConfigurationLoader.get("browser");
        logger.debug("Requesting Factory to initialize Playwright");
        PlaywrightFactory.initBrowser(browser, username, password);
        logger.debug("Requesting Factory for page object");
        page = PlaywrightFactory.getPage();
    }

    protected APIRequestContext getNewContext() {
        logger.info("Returning new Context.");
        return PlaywrightFactory.getNewAPIContext();
    }

    public void setupForGeolocation() {
        logger.info("Context Setup for Geolocation requested.");
        logger.debug("Clearing the default instance.");
        teardown();
        String browser = ConfigurationLoader.get("browser");
        logger.debug("Requesting Factory to initialize Playwright");
        PlaywrightFactory.initGeolocationBrowser(browser);
        logger.debug("Requesting Factory for page object");
        page = PlaywrightFactory.getPage();
    }

    protected Page getPage() {
        logger.info("Returning page Instance.");
        return page;
    }

    @AfterMethod
    public void teardown() {
        logger.info("Closing Playwright instance.");
        PlaywrightFactory.tearDown();
    }
}
