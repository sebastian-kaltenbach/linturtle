package com.ibm.model;

import org.apache.maven.plugin.logging.Log;

import com.typesafe.config.Config;

public class Runner {
    
    private final Config config;
    private final Log logger;

    public Runner(final Config config, final Log logger) {
        this.config = config;
        this.logger = logger;

        logger.info(config.getString("path"));
        logger.info(config.getString("format"));
    }
}
