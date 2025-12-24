package org.example.dao;

import org.example.bean.Student;

public interface StudentDAO {
    void saveStudent(Student student);
    Student getStudent();
}
