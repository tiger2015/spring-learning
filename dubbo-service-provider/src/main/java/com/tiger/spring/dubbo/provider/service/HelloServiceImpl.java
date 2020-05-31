package com.tiger.spring.dubbo.provider.service;


import com.tiger.spring.dubbo.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        log.info("call sayHello");
        return "hello, " + name;
    }
}
