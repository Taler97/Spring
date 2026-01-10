package org.example.service;

import org.example.bean.Grades;
import org.example.bean.Student;

public class GradesService {
    public static int printTotal(Student student) {
        Grades grades = student.getGrades();
        int sum=grades.getChinese()+ grades.getEnglish()+ grades.getMath()+ grades.getGeography();
        System.out.println("当前总分"+sum);
        return sum;
    }
    public static Student improveSubjectGradeDirect(Student student) {
        Grades grades = student.getGrades();
        if (grades == null) return student;

        if ( grades.getChinese() < 60) {
            System.out.println("语文"+grades.getChinese()+"不及格");
            int improved = (int) Math.ceil(grades.getChinese() * 1.1);
            grades.setChinese(Math.min(improved, 100));
        }
        if (grades.getMath() < 60) {
            System.out.println("数学"+grades.getMath()+"不及格");
            int improved = (int) Math.ceil(grades.getMath() * 1.1);
            grades.setMath(Math.min(improved, 100));
        }

        if ( grades.getEnglish() < 60) {
            System.out.println("英语"+grades.getEnglish()+"不及格");
            int improved = (int) Math.ceil(grades.getEnglish() * 1.1);
            grades.setEnglish(Math.min(improved, 100));
        }

        if (grades.getGeography() < 60) {
            System.out.println("地理"+grades.getGeography()+"不及格");
            int improved = (int) Math.ceil(grades.getGeography() * 1.1);
            grades.setGeography(Math.min(improved, 100));
        }
        System.out.println("已提升不及格科目10%");
        return student;
    }
    public static double getAvrGoals(Student student){
        int sum = printTotal(student);
        double avr= (double) sum /4;
        System.out.println("平均分数"+avr);
        return avr;
    }
}
