package com.tiger.spring.dubbo.consumer;

import com.tiger.spring.dubbo.consumer.config.DubboConsumerConfig;
import com.tiger.spring.dubbo.consumer.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class DubboServiceConsumer {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DubboConsumerConfig.class);
        MyService service = context.getBean(MyService.class);
        log.info(service.hello("tiger"));
//        System.out.println(service.hello("tiger"));
    }
}
