package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、配置类 @SpringBootConfiguration
 * 2、自动加载配置 @EnableAutoConfiguration 自动加载其他的配置类
 * 3、@ComponentScan 默认会扫描所在类的包及其子包中的组件
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        //1、自动创建IOC容器，加载配置
        //2、启动tomcat服务器软件
        SpringApplication.run(Main.class, args);
    }
}