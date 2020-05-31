package com.tiger.spring.dubbo.consumer.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = {"com.tiger.spring.dubbo.consumer.service"})
@PropertySource(value={"classpath:dubbo-consumer.properties"})
@ComponentScan(basePackages = {"com.tiger.spring.dubbo.consumer.service"})
public class DubboConsumerConfig {
}
