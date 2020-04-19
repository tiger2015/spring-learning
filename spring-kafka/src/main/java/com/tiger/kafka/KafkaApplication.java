package com.tiger.kafka;

import com.tiger.kafka.service.KafkaProducerService;
import com.tiger.kafka.service.KafkaProducerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.tiger.kafka.config.ApplicationConfig;


public class KafkaApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","consumer,concurrent,producer");
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        KafkaProducerService<String, String> producerService = ApplicationConfig.getBean(KafkaProducerServiceImpl.class);
        for (int i = 0; i < 1000; i++) {
            producerService.send("result", i + "", i + "");
        }

    }


}
