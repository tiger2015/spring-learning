package com.tiger.spring.dubbo.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@EnableDubbo(scanBasePackages = {"com.tiger.spring.dubbo.provider.service"})
@PropertySource(value = {"classpath:dubbo-provider.properties"})
public class ServiceProviderConfig {

}
