package com.tiger.spring.dubbo.consumer.service;

import com.tiger.spring.dubbo.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @DubboReference(check = true)
    private HelloService helloService;

    public String hello(String name) {
        return helloService.sayHello(name);
    }

}
