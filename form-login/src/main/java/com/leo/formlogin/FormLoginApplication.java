package com.leo.formlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FormLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormLoginApplication.class,args);
    }
}
