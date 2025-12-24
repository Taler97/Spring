package org.example.service;

import org.example.bean.Card;
import org.example.bean.Student;

import java.util.List;

public class LibraryManager {
    public static void checkCard(List<Student> students){
        for (Student student : students) {
            Card studentCard = student.getCard();
            String type = studentCard.getType();
            String color = studentCard.getColor();
            if (type.equals("A")){
                if (color.equals("red")){
                    System.out.println("java");
                } else if (color.equals("blue")) {
                    System.out.println("orade");
                }
            }
            else if (type.equals("B")) {
                if (color.equals("red")){
                    System.out.println("html");
                }else {
                    System.out.println("三国演义");
                }
            }else {
                System.out.println("三国演义");
            }
        }
    }
}
