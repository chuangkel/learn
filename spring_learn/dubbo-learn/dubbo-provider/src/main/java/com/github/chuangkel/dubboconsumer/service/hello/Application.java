package com.github.chuangkel.dubboconsumer.service.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

//@SpringBootApplication
//@EnableDubboConfiguration
public class Application {

    public static void main(String[] args) throws IOException {
        //SpringApplication.run(Application.class, args);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/hello-service.xml");
        context.start();

        System.out.println("Hello service started");
        // press any key to exit
        System.in.read();
    }

}
