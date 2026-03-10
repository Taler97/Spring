package org.example;


import org.example.bean.imp.SaleMan;
import org.example.bean.imp.User;
import org.example.bean.imp.UserManager;
import org.example.bean.imp.Waiter;
import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class  AopTest{
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SaleMan saleMan = context.getBean(SaleMan.class);
        saleMan.order();
        saleMan.pay();

        Waiter waiter=context.getBean(Waiter.class);
        waiter.order();
        waiter.pay();

        UserManager userManager=context.getBean(UserManager.class);
        User user1=new User("张三","HUNAN","BEIJING");
        userManager.talk(user1);
        User user2=new User("李四","GUANGDONG","HUNAN");
        userManager.talk(user2);
        }
    }
