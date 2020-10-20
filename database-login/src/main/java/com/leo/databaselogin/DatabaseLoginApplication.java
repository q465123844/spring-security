package com.leo.databaselogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DatabaseLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseLoginApplication.class,args);
    }
}
