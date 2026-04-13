package org.example;


import org.example.entity.Account;
import org.example.config.MyBatisConfig;
import org.example.service.AccountService;
import org.example.service.InitDataBases;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        AccountService accountService = context.getBean(AccountService.class);
        InitDataBases initDataBases=context.getBean(InitDataBases.class);
        initDataBases.initDatabase();
        Account user1 = new Account();
        user1.setAccount("zhangsan");
        user1.setPassword("123456");
        accountService.createAccount(user1);

        Account user2 = new Account();
        user2.setAccount("lisi");
        user2.setPassword("123456");
        accountService.createAccount(user2);

        List<Account> users = accountService.listAllAccounts();
        users.forEach(user -> {
            System.out.println("ID: " + user.getId() +
                    ", 账号: " + user.getAccount() +
                    ", 点券: " + user.getCoupon());
        });

        accountService.login("zhangsan", "123456");
        accountService.login("zhangsan", "wrongpassword");


//        try {
//            accountService.recharge("zhangsan", 500);
//        } catch (Exception e) {
//            System.out.println("充值失败，事务回滚: " + e.getMessage());
//        Account afterRecharge = accountService.login("zhangsan", "123456");
//        System.out.println("充值后的点券: " + afterRecharge.getCoupon());

        try{
            accountService.consume("zhangsan",300);
        }catch (Exception e1){
            System.out.println("充值失败"+e1.getMessage());
        Account recharged=accountService.login("zhangsan","123456");
            System.out.println("充值后的点券: " + recharged.getCoupon());
        }

    }
    }
