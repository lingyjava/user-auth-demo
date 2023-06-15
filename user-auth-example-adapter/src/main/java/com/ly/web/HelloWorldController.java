package com.ly.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ling yuan
 * @version 1.0.0
 * @createTime 2023年06月15日 10:08:00
 */
@RestController
public class HelloWorldController {

    @GetMapping(value = "/helloworld")
    public String helloWorld(){
        return "Hello, world!";
    }
}
