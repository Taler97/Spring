package org.example.service;

import lombok.Data;

import org.example.bean.Teachers;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

@Data
@Configuration
public class TeacherService {
    private Teachers teacher;


    public  Teachers getTeacherBySpring(){

        return this.teacher;
    }
    public static Teachers getTeachersByReflect() throws NoSuchFieldException, IllegalAccessException {
        Teachers teachers=new Teachers();
        Field name = teachers.getClass().getDeclaredField("name");
        name.setAccessible(true);
        name.set(teachers,"张三");

        Field addr = teachers.getClass().getDeclaredField("addr");
        addr.setAccessible(true);
        addr.set(teachers,"北京");
        return teachers;
    }
}
