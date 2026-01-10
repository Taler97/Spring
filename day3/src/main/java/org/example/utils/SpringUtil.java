package org.example.utils;

import org.example.config.DataSourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringUtil {
    public static ApplicationContext getContext(){
        return new AnnotationConfigApplicationContext(DataSourceConfig.class);
    }
}