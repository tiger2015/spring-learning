package com.tiger.springlearning.aspectj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true ) //是否使用CGLIB代理
@ComponentScan(basePackages = {"com.tiger.springlearning"})
public class ApplicationConfig {

    @Bean
    public Audience audience(){
        return new Audience();
    }

    @Bean
    public Performance performance(){
        return new MusicPerformance();
    }

    @Bean
    public Encoreable defaultEncoreable(){
        return new DefaultEncoreable();
    }
}
