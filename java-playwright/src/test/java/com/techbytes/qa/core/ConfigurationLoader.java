package com.techbytes.qa.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tahreem J. Naseem (www.tahreems.com / +92-333-443-8775) {TECHBYTES}
 */

/**
 * Configurations loader Class for loading values mention in configuration.properties file
 */
public class ConfigurationLoader {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

    public static Properties props;

    static {
        logger.info("Setting up the Configuration Loading Service.");
        try {
            logger.debug("Creating the Properties instance.");
            props = new Properties();
            logger.debug("Creating InputStream instance of the configuration file.");
            InputStream is = ConfigurationLoader.class.getClassLoader().getResourceAsStream("configuration.properties");
            logger.debug("Loading the configuration file.");
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration properties. Reason: ", e);
        }
    };
    
    public static String get(String key) {
        logger.info("Returning {} configuration.", key);
        return props.getProperty(key);
    }
}
