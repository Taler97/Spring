package org.example.bean.imp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User implements org.example.bean.inter.User {
    String name;
    String start;
    String end;
}
