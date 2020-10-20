package com.leo.databaselogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan(basePackages = "com.leo.databaselogin")
public class DatabaseLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(DatabaseLoginApplication.class,args);
    }
}
