package org.example.service;

import org.example.bean.Student;

import java.util.List;

public class BathRoomManager {

    public static void checkGender(List<Student> students){
        for (Student student:students) {
            if (student.getGender().equals("男")){
                System.out.println(student.getName()+"可进入浴池");
            }
        }
    }

}
