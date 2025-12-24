package org.example.service;

import lombok.Data;
import org.example.bean.Teacher;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
public class TeacherService {
    private Teacher teacher;


    public  Teacher getTeacherBySpring(){

        return this.teacher;
    }
}
