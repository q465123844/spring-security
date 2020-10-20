package com.leo.databaselogin.auth.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext application;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.application=applicationContext;
    }

    public static ApplicationContext getApplication() {
        return application;
    }
}
