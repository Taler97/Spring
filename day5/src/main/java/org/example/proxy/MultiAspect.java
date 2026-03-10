package org.example.proxy;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.bean.imp.User;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Aspect
@Component
public class MultiAspect {

     private static final Log log = LogFactory.getLog(MultiAspect.class);

     @Before("execution(* org.example.bean.imp.SaleMan.order(..))")
     public void beforeOrder1() {
          System.out.println("您好，欢迎光临");
     }
     @After("execution(* org.example.bean.imp.SaleMan.pay(..))")
     public void afterPayment(){
          System.out.println("慢走欢迎再来");
     }


     @Before("execution(* org.example.bean.imp.Waiter.order(..))")
     public void beforeWaiterOrder(){
          System.out.println("您好，欢迎光临");
     }
     @After("execution(* org.example.bean.imp.Waiter.pay(..))")
     public void afterWaiterPay(){
          System.out.println("请带好您的随身物品,慢走欢迎再来");
     }

     @After("execution(* org.example.bean.imp.UserManager.talk(..)) && args(user)")
     public void isHighRiskArea(User user){
          ResourceBundle resourceBundle=ResourceBundle.getBundle("HighRiskAreas");
          String start=user.getStart();
          String end=user.getEnd();
          if (resourceBundle.getString(start).equals("1")){
               System.out.println("您的出发地为高风险地区，禁止出行");
          }
          if (resourceBundle.getString(end).equals("1")){
               System.out.println("您的目的地为高风险地区，已记录行程");
               org.example.log.Log log = org.example.log.Log.createLog(user);
               org.example.log.Log.showLog(log);
          }
     }
}
