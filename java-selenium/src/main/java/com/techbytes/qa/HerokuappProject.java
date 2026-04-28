package com.techbytes.qa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */
public class HerokuappProject {
    
    private static final Logger logger = LoggerFactory.getLogger(HerokuappProject.class);

    public static void main(String[] args) {
        try {
            logger.info(" -> Starting QA Automation Master Selenium Project...");
        } catch (Exception e) {
            logger.debug(" -> Error Starting QA Automation Master Selenium Project: {}", e.getMessage());
        }
    }
}
