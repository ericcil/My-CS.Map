package com.vcredit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author chenyubin
 * @Date 2018/7/2
 * @Description 启动类
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.vcredit")
public class ApplicationStarter {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
