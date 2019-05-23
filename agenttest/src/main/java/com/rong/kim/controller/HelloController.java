package com.rong.kim.controller;

import com.rong.kim.common.Lion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() throws InterruptedException {
        Lion lion = new Lion();
        lion.runLion();
        return "Hi";
    }
}
