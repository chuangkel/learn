package com.github.springboot.controller;

import com.github.springboot.domain.Person;
import com.github.springboot.service.DemoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-04-15 10:40
 **/

@RestController
public class DemoTestController {

    @Autowired
    DemoTestService demoTestService;
    @RequestMapping("/test/demo")
    public Person testDemo(){
        Person person = new Person(1,"tom",2,2.00);
        return person;
        //demoTestService.testDemo();
    }
}
