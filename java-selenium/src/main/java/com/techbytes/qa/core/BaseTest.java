package com.techbytes.qa.core;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */

//  Creating a BaseTest Class for centralized management and maintenance by creating general utility methods.
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    public static int testCounter = 0;
    
//    @BeforeGroups(alwaysRun = true)
//    public static void setUpGroups() {
//        logger.info(" -> ===== TestNG TEST GROUPS STARTING =====");
//    }
//    
//    @AfterGroups(alwaysRun = true)
//    public static void teardownGroups() {
//        logger.info(" -> ===== TEST GROUPS COMPLETED =====");
//    }
//    
//    
    @BeforeSuite(alwaysRun = true)
    public static void setupSuite() {
        logger.info(" -> ===== TestNG TEST SUITE STARTING =====");
        logger.info(" -> Java Version: {}.", System.getProperty("java.version"));
        logger.info(" -> Start Time: {}.", java.time.LocalDateTime.now());
    }
    
    @AfterSuite(alwaysRun = true)
    public static void teardownSuite() {
        logger.info(" -> ===== TEST SUITE COMPLETED =====");
        logger.info(" -> Total Tests Executed: {}.", testCounter);
        logger.info(" -> End Time: {}.", java.time.LocalDateTime.now());
    }
    
//    @BeforeClass(alwaysRun = true)
//    public static void setupClass() {
//        logger.info(" -> ===== TestNG TEST CLASS STARTING =====");
//    }
//    
//    @AfterClass(alwaysRun = true)
//    public static void teardownClass() {
//        logger.info(" -> ===== TEST CLASS COMPLETED =====");
//    }
//    
//    @BeforeTest(alwaysRun = true)
//    public static void setupTest(ITestContext context) {
//        logger.info(" -> ===== TestNG TEST STARTING =====");
//    }
//    
//    @AfterTest(alwaysRun = true)
//    public static void tearDownTest() {
//        logger.info(" -> ===== TEST COMPLETED =====");
////        DriverFactory.quitDriver();
////        logger.info(" -> Tear down process completed...");
//    }

    @BeforeMethod(alwaysRun = true)
    public static void setupMethod() {
        logger.info(" -> ===== TestNG TEST METHOD STARTING =====");
        testCounter++;
    }

    @AfterMethod(alwaysRun = true)
    public static void tearDownMethod() {
        logger.info(" -> ===== TEST METHOD COMPLETED =====");
        logger.info(" -> Starting up the tear down process of test conditions set at start of the test.");
        DriverFactory.quit();
        logger.info(" -> Tear down process completed...");
    }
    
    protected WebDriver getDriver() {
        logger.info(" -> Returning WebDriver...");
        return DriverFactory.init(ConfigurationLoader.get("browser"));
    }
}
