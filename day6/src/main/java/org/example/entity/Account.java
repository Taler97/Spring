package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account{
    int id;
    String account;
    String password;
    int coupon;
}
