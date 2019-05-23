package com.github.chuangkel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-05-05 10:41
 **/

@SpringBootApplication
@ServletComponentScan("com/github/chuangkel/servlet")
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class);
    }
}
