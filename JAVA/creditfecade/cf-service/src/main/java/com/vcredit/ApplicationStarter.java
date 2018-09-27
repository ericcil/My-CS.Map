package com.vcredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 启动类
 * @Author chenyubin
 * @Date 2018/7/2
 */
//@EnableCaching
@EnableAutoConfiguration
@ComponentScan("com.vcredit")
public class ApplicationStarter{


    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStarter.class, args);
    }


}
