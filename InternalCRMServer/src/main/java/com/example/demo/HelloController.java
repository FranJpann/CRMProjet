package com.example.demo;

import internalcrm.model.InternalCRMImpl;
import internalcrm.thrift.InternalCRMService;
import org.apache.thrift.TException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
 
    @RequestMapping("/index")
    public String getLead() {
        return "ok";
    }  
}