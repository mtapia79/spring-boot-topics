package com.example.demo.web;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.SampleService;

@RestController
public class SampleController {
 
    @Autowired
    private SampleService sampleService;
    
    private static final Logger log = LogManager.getLogger(SampleController.class);
 
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }
 
    @RequestMapping(value = "/welcome/{userName}", method = RequestMethod.GET)
    public String welcome(
            @PathVariable("userName") String userName
    ) 
    {
        String greeting = sampleService.welcome(userName);
        
        if(greeting == null) {
            throw new UserNotFoundException("user not found:" + userName);
        }
        return greeting; 
    }
    
    @RequestMapping(value = "/interceptor", method = RequestMethod.GET)
    public String interceptorExample() {
        log.info("te intercepte");
        return "despues de interceptar";
    }
}
