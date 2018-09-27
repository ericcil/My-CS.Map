package com.vcredit;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author chenyubin
 * @Date 2018/7/12
 */
public class ServletContainerStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(ApplicationStarter.class);
        return super.configure(builder);
    }
}
