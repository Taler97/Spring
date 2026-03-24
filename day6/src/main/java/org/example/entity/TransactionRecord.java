package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecord {
    private Integer id;           // 主键ID
    private String account;       // 帐号
    private Date updateTime;      // 发生时间
    private String category;      // 类型：充值|消费
    private Integer num;          // 数量
}
