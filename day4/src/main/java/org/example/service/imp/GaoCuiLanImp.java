package org.example.service.imp;

import org.example.service.rolesInter.GaoCuiLan;
import org.springframework.stereotype.Component;

@Component("GaoCuiLanImp")
public class GaoCuiLanImp implements GaoCuiLan {
    @Override
    public void catchPig() {
        System.out.println("高翠兰发动抓猪");
    }

    @Override
    public String getName() {
        return "高翠兰";
    }

    @Override
    public void appearance() {
        System.out.println("当前样貌:高翠兰");
    }

    @Override
    public void ability() {
        System.out.println("我是高翠兰");
    }
}
