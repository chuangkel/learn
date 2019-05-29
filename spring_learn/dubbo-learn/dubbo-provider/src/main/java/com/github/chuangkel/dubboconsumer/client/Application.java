package com.github.chuangkel.dubboconsumer.client;

import com.github.chuangkel.dubboconsumer.api.GreetingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/client.xml");
        context.start();
        // 获取远程代理并发起调用
        GreetingService greetingService = (GreetingService) context.getBean("greetingService");
        System.out.println(greetingService.greeting("world"));
    }
}