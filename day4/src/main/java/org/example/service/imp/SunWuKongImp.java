package org.example.service.imp;

import org.example.service.Role;
import org.springframework.stereotype.Component;

@Component("SunWuKongImp")
public class SunWuKongImp implements org.example.service.rolesInter.SunWuKong {
    @Override
    public void transform(Object target) {
        System.out.println("孙悟空发动变身");
    }

    @Override
    public void fight(Object target) {
        if (target instanceof Role) {
            Role enemy = (Role) target;
            String enemyName = enemy.getName();
            System.out.println("孙悟空正在与" + enemyName + "战斗");
        } else {
            System.out.println("目标不是角色，无法战斗");
        }
    }

    @Override
    public String getName() {
        return "孙悟空";
    }

    @Override
    public void appearance() {
        System.out.println("当前样貌：孙悟空");
    }

    @Override
    public void ability() {

    }
}
