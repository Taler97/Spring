package org.example.bean.imp;

import org.springframework.stereotype.Component;

@Component
public class SaleMan implements org.example.bean.inter.SaleMan {

    @Override
    public void order() {
        System.out.println("购买什么商品");
    }

    @Override
    public void pay() {
        System.out.println("请扫码支付");
    }
}
