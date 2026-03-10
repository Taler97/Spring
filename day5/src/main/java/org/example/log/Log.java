package org.example.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bean.imp.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Log {
    User user;
    LocalDateTime time;
    public static Log createLog(User user){
        return new Log(user,LocalDateTime.now());
    }
    public static void showLog(Log log){
        System.out.println("姓名:"+log.getUser().getName()+"\n"+
                            "出发地："+log.getUser().getStart()+"\n"+
                            "目的地:"+log.getUser().getEnd()+"\n"+
                            "时间："+log.getTime().toString());
    }
}
