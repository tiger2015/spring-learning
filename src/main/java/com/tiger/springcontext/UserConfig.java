package com.tiger.springcontext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class UserConfig {
    public static String name;
    public static String password;


    @Value("${user.name:}")
    public void setName(String name) {
        UserConfig.name = name;
        log.info("user.name:{}", name);
    }

    @Value("${user.password:}")
    public void setPassword(String password) {
        UserConfig.password = password;
        log.info("user.password:{}", password);
    }
}
