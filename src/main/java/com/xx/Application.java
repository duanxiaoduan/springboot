package com.xx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by jishu02 on 2017/4/5.
 */
@SpringBootApplication
@PropertySource(value = {"classpath:application-bimt.properties", "classpath:application-bimt-env.properties"}, ignoreResourceNotFound = true)
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
