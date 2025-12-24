package org.example;

import org.example.Utils.SpringUtil;
import org.example.bean.Teacher;
import org.example.controller.Day2;
import org.example.service.BathRoomManager;
import org.example.service.StudentService;
import org.example.service.TeacherService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TeacherTest {
    public static void main(String[] args) {
        Day2 day2=new Day2();
        day2.dayDesk();
    }

}
