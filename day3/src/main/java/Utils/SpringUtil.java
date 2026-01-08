package Utils;

import config.DataSourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
    public static ApplicationContext getContext(){
        return new AnnotationConfigApplicationContext(DataSourceConfig.class);
    }
}