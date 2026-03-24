package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecord {
    private Integer id;
    private String account;
    private Date updateTime;
    private String category;
    private Integer num;
}
