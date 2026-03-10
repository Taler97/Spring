package org.example.bean.imp;

import org.springframework.stereotype.Component;

@Component
public class Waiter implements org.example.bean.inter.Waiter {

    @Override
    public void order() {
        System.out.println("请看一下菜单");
    }

    @Override
    public void pay() {
        System.out.println("请扫码支付");

    }
}
