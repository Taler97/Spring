package org.example.service.proxy;


import org.example.service.rolesInter.GaoCuiLan;
import org.example.service.rolesInter.SunWuKong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component("gaoCuiLanProxy")
public class GaoCuiLanStaticProxy implements GaoCuiLan {

    @Autowired
    private GaoCuiLan gaoCuiLanImp;

    @Autowired
    private SunWuKong sunWuKongImp;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @Override
    public String getName() {
        sunWuKongImp.transform(gaoCuiLanImp);
        String realName = gaoCuiLanImp.getName();
        return "孙悟空伪装的" + realName;
    }

    @Override
    public void ability() {
        sunWuKongImp.transform(gaoCuiLanImp);
        sunWuKongImp.ability();
    }

    @Override
    public void appearance() {
        sunWuKongImp.transform(gaoCuiLanImp);
        gaoCuiLanImp.appearance();
        System.out.println("（实际是孙悟空变化）");
    }


    @Override
    public void catchPig() {
        System.out.println("孙悟空抓猪");
    }
}