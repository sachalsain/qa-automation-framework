package com.techbytes.qa.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver init(String browserName) {
        
        logger.info(" -> Checking if driver exists...");
        if (driver.get() == null) {

            BrowserType browser = BrowserType.valueOf(browserName.toUpperCase());
            switch (browser) {
                case CHROME -> {
                    logger.debug(" -> Setting Chrome options...");
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--disable-dev-shm-usage");
//                    options.addArguments("--no-sandbox");
//                    options.addArguments("--disable-gpu");
//                    options.addArguments("--start-maximized");
//                    logger.info(" -> Setting Chrome Driver Instance with options...");
//                    driver.set(new ChromeDriver(options));
                    logger.debug(" -> Setting Chrome Driver Instance...");
                    driver.set(new ChromeDriver());
                }
                case FIREFOX -> {
                    logger.debug(" -> Setting firefox Driver Instance...");
                    driver.set(new FirefoxDriver());
                }
                case EDGE -> {
                    logger.debug(" -> Setting Edge Driver Instance...");
                    driver.set(new EdgeDriver());
                }
                default -> {
                    throw new IllegalArgumentException("Unsupported Browser: " + browser);
                }
            }
        }
        logger.debug(" -> Returning New Web Driver...");
        return driver.get();
    }
    
    public static WebDriver get() {
        logger.info(" -> Returning Web Driver...");
        return driver.get();
    }

    public static void quit() {
        if (driver != null) {
            logger.info(" -> Quitting Driver...");
            driver.get().quit();
            logger.debug(" -> Destroying Driver Instance...");
            driver.remove();
        }
    }
}
