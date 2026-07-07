package com.tech.eccomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.eccomerce.constants.AppConstants;

@RestController
public class Controller {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from " + AppConstants.APP_NAME;
    }

}
