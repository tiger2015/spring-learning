package com.tiger.springlearning;

import com.tiger.springlearning.bean.ClientService;
import com.tiger.springlearning.bean.CommandManager;
import com.tiger.springlearning.bean.ExampleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Application {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        ExampleBean exampleBean = context.getBean("exampleBean", ExampleBean.class);
        exampleBean.print();
        ClientService clientService = context.getBean("clientService", ClientService.class);
        CommandManager commandManager = context.getBean("commandManager", CommandManager.class);
        commandManager.process();
    }
}
