package com.tiger.springlearning;

import com.tiger.springlearning.aspectj.ApplicationConfig;
import com.tiger.springlearning.aspectj.DefaultEncoreable;
import com.tiger.springlearning.aspectj.MusicPerformance;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationContext {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MusicPerformance performance = context.getBean("performance", MusicPerformance.class);
        performance.perform();

        DefaultEncoreable encoreable = context.getBean("defaultEncoreable", DefaultEncoreable.class);
        encoreable.performEncore();

    }
}
