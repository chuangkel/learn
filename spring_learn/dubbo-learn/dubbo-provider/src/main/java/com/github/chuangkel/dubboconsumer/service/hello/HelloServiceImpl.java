package com.github.chuangkel.dubboconsumer.service.hello;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.chuangkel.dubboconsumer.api.HelloService;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @program: spring_learn
 * @description:
 * @author: chuangkel
 * @create: 2019-05-29 09:25
 **/
@Service(interfaceClass = HelloService.class)
@Component
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        try {
            // 通过 sleep 模拟业务逻辑处理时间
            Thread.sleep(new Random(System.currentTimeMillis()).nextInt(1000));
        } catch (InterruptedException e) {
            // no op
        }
        return "Hello " + name;
    }
}
