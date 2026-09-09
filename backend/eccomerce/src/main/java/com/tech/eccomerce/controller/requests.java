package com.tech.eccomerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tech.eccomerce.constants.AppConstants;

@RestController
public class requests {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "Hello from " + AppConstants.APP_NAME;
    }
    @RequestMapping(value = "/world", method = RequestMethod.POST)
    public String world() {
        return "World from " + AppConstants.APP_NAME;
    }
}
