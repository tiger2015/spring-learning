package com.tiger.springcontext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(value = {UserConfig.class})
@PropertySource(value = {"classpath:application.properties"}, ignoreResourceNotFound = true)
@Slf4j
public class ApplicationConfig {
    public static String flag;

    @Value("${server.flag:}")
    public void setFlag(String flag) {
        ApplicationConfig.flag = flag;
        log.info("server.flag:{}", flag);
    }
}
