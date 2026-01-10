package org.example;

import org.example.bean.Student;
import org.example.bean.Teachers;
import org.example.service.StudentService;
import org.example.service.TeacherService;
import org.example.utils.SpringUtil;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.List;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        StudentService studentService = SpringUtil.getContext().getBean(StudentService.class);
        List<Student> students = studentService.getStudents();
        studentService.saveStudents(students);

        Teachers teachers=TeacherService.getTeachersByReflect();
        System.out.println(teachers);

        System.out.println(SpringUtil.getContext().getBean("teacher3",Teachers.class));
    }
    }