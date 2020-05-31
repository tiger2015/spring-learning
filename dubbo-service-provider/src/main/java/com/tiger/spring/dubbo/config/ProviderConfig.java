package com.tiger.spring.dubbo.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = {"com.tiger.spring.dubbo.service"})
@PropertySource(value = {"classpath:dubbo-provider.properties"})
public class ProviderConfig {
}
