package org.example.bean.imp;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class UserManager  implements org.example.bean.inter.UserManager
{


    @Override
    public void talk(User user) {
        System.out.println("您好，"+user.getName()+",欢迎您乘坐从"+user.getStart()+"到"+user.getEnd()+"的航班");
    }
}
