package com.github.chuangkel.mongodb_learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
//@SpringBootApplication
public class MongodbLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbLearnApplication.class, args);
    }

}
