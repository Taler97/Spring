package org.example.Utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
    static final String configLocation="spring-beans.xml";
    public static ApplicationContext getContext(){
        return new ClassPathXmlApplicationContext(configLocation);
    }
}
