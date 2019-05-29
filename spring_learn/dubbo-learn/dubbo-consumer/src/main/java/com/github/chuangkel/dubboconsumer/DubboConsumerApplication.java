package com.github.chuangkel.dubboconsumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.github.chuangkel.dubboconsumer.service.HelloConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDubboConfiguration
@RestController
public class DubboConsumerApplication {

    @Autowired
    private HelloConsumer helloConsumer;

    //public DubboConsumerApplication(HelloConsumer helloConsumer) {
    //    this.helloConsumer = helloConsumer;
    //}

    @RequestMapping(value = "/get")
    public void get(){
        helloConsumer.say("tom");
    }
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }

}
