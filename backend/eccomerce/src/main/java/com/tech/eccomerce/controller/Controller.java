package com.tech.eccomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.eccomerce.constants.AppConstants;

@RestController
@RequestMapping("/api")
public class Controller {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from " + AppConstants.APP_NAME;
    }
    @GetMapping("/world")
    public String world(@RequestParam String name){
        return "World from "+ name + AppConstants.APP_NAME;
    }
    
}
