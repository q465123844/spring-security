package com.leo.httpbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HttpBasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpBasicApplication.class,args);
    }
}
