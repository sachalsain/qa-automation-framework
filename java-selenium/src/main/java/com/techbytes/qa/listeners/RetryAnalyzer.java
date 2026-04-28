package com.techbytes.qa.listeners;

import com.techbytes.qa.core.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger logger = LoggerFactory.getLogger(RetryAnalyzer.class);

    private int count = 0;
    private static final int MAX_RETRY = Integer.parseInt(ConfigurationLoader.get("maxRetryCount"));

    @Override
    public boolean retry(ITestResult result) {
        // return count++ < MAX_RETRY;
       if (count < MAX_RETRY) {
           count++;
            logger.info(" -> Retrying number: {}.", count);
           return true;
       }
       return false;
    }
}
