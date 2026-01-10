package org.example.controller;

import lombok.Data;
import org.example.utils.SpringUtil;
import org.example.bean.Student;
import org.example.service.BathRoomManager;
import org.example.service.GradesService;
import org.example.service.LibraryManager;
import org.example.service.StudentService;

import java.util.List;

@Data
public class Day2 {

    public static void dayDesk(){
        StudentService bean = SpringUtil.getContext().getBean("studentService", StudentService.class);
        List<Student> studentList = bean.getStudentList();
        BathRoomManager.checkGender(studentList);
        LibraryManager.checkCard(studentList);
        GradesService.printTotal(studentList.get(0));
        GradesService.improveSubjectGradeDirect(studentList.get(0));
        GradesService.getAvrGoals(studentList.get(0));
    }

}
