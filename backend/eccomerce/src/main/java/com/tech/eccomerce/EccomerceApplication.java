package com.tech.eccomerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EccomerceApplication {

    private static final Logger log = LoggerFactory.getLogger(EccomerceApplication.class);

    public static void main(String[] args) {
        log.info("Starting Eccomerce application...");
        SpringApplication.run(EccomerceApplication.class, args);
        log.info("Eccomerce application started successfully.");
    }

}
