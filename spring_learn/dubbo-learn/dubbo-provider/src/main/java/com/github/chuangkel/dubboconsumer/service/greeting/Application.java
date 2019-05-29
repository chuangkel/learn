package com.github.chuangkel.dubboconsumer.service.greeting;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/greeting-service.xml");
        context.start();

        System.out.println("Greeting service started");
        // press any key to exit
        System.in.read();
    }
}