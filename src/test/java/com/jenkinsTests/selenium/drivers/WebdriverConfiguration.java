package com.jenkinsTests.selenium.drivers;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

public class WebdriverConfiguration {
    private static final String LOG4J_SELENIUM_PROPERTIES = "log4j.selenium.properties";
    private static CompositeConfiguration config = new CompositeConfiguration();
    private static Log logger;

    static {
        try {
            initializeConfig();
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializeConfig() throws ConfigurationException {
        SystemConfiguration systemConfiguration = new SystemConfiguration();
        String userHome = systemConfiguration.getString("user.home");
        String configHome = userHome + File.separator + "ohm";
        // first: make sure the logging framework is configured
        File log4jConf = new File(configHome + File.separator + LOG4J_SELENIUM_PROPERTIES);

//        logger = LogFactory.getLog(WebdriverConfiguration.class);
//        if (log4jConf.exists()) {
//            logger.info("Log4j properties loaded from " + log4jConf.getAbsolutePath());
//        } else {
//            logger.info("Log4j properties loaded from log4j.selenium.properties on the classpath, you can override this by creating $HOME/ohm/log4j.selenium.properties");
//        }
        // loggers are allowed after this point:
        try{
            PropertiesConfiguration localPropertiesConfiguration = new PropertiesConfiguration(configHome + File.separator + "config.local.properties");
            //config.addConfiguration(localPropertiesConfiguration);
            //logger.info("local config file has been found in directory: " + configHome);
        } catch (Exception e) {
            //logger.error("no local config file has been found in directory: " + configHome);
            //logger.error("if you want a custom configuration, create the file: config.local.properties in directory: " + configHome);
            // DO NOTHING, LOCAL CONFIG HAS NOT BEEN DEFINED
        }
        config.addConfiguration(new PropertiesConfiguration("config.properties"));
    }

    public static CompositeConfiguration getConfig() {
        if (config.isEmpty()) {
            try {
                initializeConfig();
            } catch (ConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        return config;
    }
}
