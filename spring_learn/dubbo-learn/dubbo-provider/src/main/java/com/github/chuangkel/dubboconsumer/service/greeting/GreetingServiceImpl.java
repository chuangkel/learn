package com.github.chuangkel.dubboconsumer.service.greeting;

import com.github.chuangkel.dubboconsumer.api.GreetingService;
import com.github.chuangkel.dubboconsumer.api.HelloService;

import java.util.Random;

public class GreetingServiceImpl implements GreetingService {
	// 下游依赖服务，运行时靠 spring 容器注入 HelloService 的服务代理
    private HelloService helloService;

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String greeting(String message) {
        try {
            // 通过 sleep 模拟业务逻辑处理时间
            Thread.sleep(new Random(System.currentTimeMillis()).nextInt(1000));
        } catch (InterruptedException e) {
            // no op
        }
        return "greeting, " + helloService.hello(message);
    }
}