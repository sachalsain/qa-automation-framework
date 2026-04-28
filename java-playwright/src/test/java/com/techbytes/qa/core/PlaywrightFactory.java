package com.techbytes.qa.core;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Geolocation;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */

/**
 * Responsible for launching Playwright and browser instance.
 */
public class PlaywrightFactory {

    private static final Logger logger = LoggerFactory.getLogger(PlaywrightFactory.class);

    private static final ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static final ThreadLocal<Page> page = new ThreadLocal<>();
    
    public static void initBrowser(String browserName) {
        initBrowser(browserName, null, null);
    }

    public static void initBrowser(String browserName, String username, String password) {

        logger.info("Initializing Playwright for browser: {}", browserName);

        logger.debug("Creating Playwright object");
        playwright.set(Playwright.create());
        
        logger.debug("Setting BrowserOptions for {} browser", browserName);        
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(ConfigurationLoader.get("headless"))); //.setArgs(Arrays.asList("--start-maximized"))

        logger.debug("Setting Browser with BrowserOptions");
        switch (browserName.toUpperCase()) {
            case "CHROMIUM" -> browser.set(playwright.get().chromium().launch(options));
            case "FIREFOX" -> browser.set(playwright.get().firefox().launch(options));
            case "WEBKIT" -> browser.set(playwright.get().webkit().launch(options));
            default -> throw new RuntimeException("Unsupported browser: " + browserName);
        }
        logger.debug("Setting Browser Context");
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        if (username != null && password != null) {
            logger.debug("Setting Browser Context with Basic Auth credentials");
            contextOptions.setHttpCredentials(username, password);
        }
//        contextOptions.setViewportSize(null); //Incase the browser is maximized
        context.set(browser.get().newContext(contextOptions));
        logger.debug("Setting Page");
        page.set(context.get().newPage());
    }
    
    public static void initGeolocationBrowser(String browserName) {

        logger.info("Initializing Playwright for browser: {}", browserName);

        logger.debug("Creating Playwright object");
        playwright.set(Playwright.create());
        
        logger.debug("Setting BrowserOptions for {} browser", browserName);        
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(ConfigurationLoader.get("headless")));

        logger.debug("Setting Browser with BrowserOptions");
        switch (browserName.toUpperCase()) {
            case "CHROMIUM" -> browser.set(playwright.get().chromium().launch(options));
            case "FIREFOX" -> browser.set(playwright.get().firefox().launch(options));
            case "WEBKIT" -> browser.set(playwright.get().webkit().launch(options));
            default -> throw new RuntimeException("Unsupported browser: " + browserName);
        }
        logger.debug("Setting Browser Context");
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        logger.debug("Creating Geolocation Instance.");
        Geolocation location = new Geolocation(Double.parseDouble(ConfigurationLoader.get("locLat")), Double.parseDouble(ConfigurationLoader.get("locLong")));
        logger.debug("Adding Geolocation to Context Options.");
        contextOptions.setGeolocation(location).setPermissions(Arrays.asList("geolocation"));
        logger.debug("Adding Context Options to Browser.");
        context.set(browser.get().newContext(contextOptions));
        logger.debug("Setting Page");
        page.set(context.get().newPage());
    }

    public static Page getPage() {
        logger.info("Returning Page");
        return page.get();
    }

    public static APIRequestContext getNewAPIContext() {
        logger.info("Returning new API Request Context");
        return playwright.get().request().newContext();
    }

    public static void tearDown() {
        logger.info("Closing Playwright instance.");
        logger.debug("Closing Page.");
        page.get().close();
        logger.debug("Closing Browser Context.");
        context.get().close();
        logger.debug("Closing Browser.");
        browser.get().close();
        logger.debug("Closing Playwright.");
        playwright.get().close();
    }
}
