package com.github.chuangkel.dubboconsumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.chuangkel.dubboconsumer.api.HelloService;
import org.springframework.stereotype.Component;

/**
 * @program: spring_learn
 * @description:
 * @author: chuangkel
 * @create: 2019-05-29 09:45
 **/
@Component
public class HelloConsumer {

    @Reference(url = "dubbo://127.0.0.1:20880")
    private HelloService iHelloService;

    public void say(String name){
        iHelloService.hello(name);
    }
}
