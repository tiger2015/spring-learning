package com.tiger.springcontext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class MyApplication {

    private static ApplicationContext context;

    static {
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        log.info("init context");
    }


    public static void main(String[] args) {
        log.info("user:{}:{}", UserConfig.name, UserConfig.password);
    }

}
