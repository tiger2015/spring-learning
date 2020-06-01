package com.tiger.spring.dubbo.consumer;

import com.tiger.spring.dubbo.consumer.config.DubboConsumerConfig;
import com.tiger.spring.dubbo.consumer.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DubboServiceConsumer {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DubboConsumerConfig.class);
        MyService service = context.getBean(MyService.class);
        for(int i=0; i<100;i++){
            log.info(service.hello("tiger"));
            TimeUnit.SECONDS.sleep(1);
        }
//        System.out.println(service.hello("tiger"));
    }
}
