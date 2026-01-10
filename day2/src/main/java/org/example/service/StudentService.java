package org.example.service;

import lombok.Data;
import org.example.utils.SpringUtil;
import org.example.bean.Student;
import org.example.dao.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class StudentService implements StudentDAO {
    @Autowired
    @Qualifier("studentList")
    private List<Student> students;
    @Override
    public void saveStudent(Student student) {
        ApplicationContext context = SpringUtil.getContext();
        Student student1 =  context.getBean("student",Student.class);
        System.out.println("已存储"+student1);
    }

    @Override
    public Student getStudent() {
        return SpringUtil.getContext().getBean("student1",Student.class);
    }
    public  List<Student> getStudentList(){
        return students;
    }

}
