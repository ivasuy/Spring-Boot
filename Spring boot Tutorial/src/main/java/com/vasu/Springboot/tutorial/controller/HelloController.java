package com.vasu.Springboot.tutorial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController means this class or method is a controller also it returns a response body,
Thus whatever we do in this class that will be my REST Endpoints. */
@RestController
public class HelloController {

    /*Using property from our config file*/
//    @Value("${welcome.message}")
//    private String welcomeMessage;
    /*
    @RequestMapping -> whatever request we want to execute we map that with this method
    "/" -> Default endpoint, Whenever hit the endpoint return this string in return
    */
//    @RequestMapping(value = "/", method = RequestMethod.GET)

    /*Now rather than us @RequestMapping and then calling Get method
    we can directly use @GetMapping*/
    @GetMapping("/")
    public String helloWorld(){
        return "Hello Vasu Yadav";
    }
}
