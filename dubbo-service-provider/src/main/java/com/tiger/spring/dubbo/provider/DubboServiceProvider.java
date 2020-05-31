package com.tiger.spring.dubbo.provider;

import com.tiger.spring.dubbo.provider.config.ServiceProviderConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@Slf4j
public class DubboServiceProvider {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ServiceProviderConfig.class);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
