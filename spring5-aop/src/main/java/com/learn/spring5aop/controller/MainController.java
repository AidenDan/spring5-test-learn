package com.learn.spring5aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2020-8-23 23:04:46
 */
@RestController
public class MainController {

    @GetMapping("login")
    public Object testLoginNumbers(@RequestParam(value = "name", required = true) String name) {
        System.err.println("loginName------" + name);
        return "{loginName:"+name+"}";
    }
}
