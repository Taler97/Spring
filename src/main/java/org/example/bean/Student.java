package org.example.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Student {
    private String name;
    private String gender;
    private Card card;

    private Grades grades;
}
