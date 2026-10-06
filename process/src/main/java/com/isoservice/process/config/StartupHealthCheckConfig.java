package com.isoservice.process.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupHealthCheckConfig {
    private static final Logger log = LoggerFactory.getLogger(StartupHealthCheckConfig.class);

    private final BuildProperties buildProperties;

    public StartupHealthCheckConfig(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        log.info("----------------------- SYSTEM HEALTH CHECK BEGINS -----------------------");
        log.info("Application Name: {}", buildProperties.getName());
        log.info("Application Version: {}", buildProperties.getVersion());
        log.info("----------------------- SYSTEM HEALTH CHECK ENDS -----------------------");
    }
}
