package org.example.dao;

import org.example.bean.Student;

import java.util.List;

public interface StudentDAO {
    void saveStudents(List<Student> students);
    Student getStudent();
}
