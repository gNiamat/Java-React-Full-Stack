package com.tech.eccomerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.eccomerce.constants.AppConstants;

@RestController
@RequestMapping("/api")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @RequestMapping("/hello")
    public String hello() {
        log.info("Accessed /api/hello endpoint");
        String response = "Hello from " + AppConstants.APP_NAME;
        log.debug("Hello response generated: {}", response);
        return response;
    }

    @GetMapping("/world")
    public String world(@RequestParam String name) {
        log.info("Accessed /api/world endpoint with name: {}", name);
        String response = "World from " + name + AppConstants.APP_NAME;
        log.debug("World response generated: {}", response);
        return response;
    }

}
