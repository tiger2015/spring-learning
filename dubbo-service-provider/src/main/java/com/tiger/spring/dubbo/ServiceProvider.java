package com.tiger.spring.dubbo;

import com.tiger.spring.dubbo.config.ProviderConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServiceProvider {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfig.class);
    }
}
