package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class InitDataBases {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 初始化数据库
     */
    public void initDatabase() {
        System.out.println("========== 开始初始化数据库 ==========");

        try {
            // 1. 删除表（使用独立连接）
            dropTables();

            // 2. 创建表（使用新连接）
            createTables();

            System.out.println("========== 数据库初始化完成 ==========\n");

        } catch (Exception e) {
            System.err.println("数据库初始化失败: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("数据库初始化失败", e);
        }
    }

    /**
     * 删除所有表（每个删除操作使用独立连接）
     */
    private void dropTables() {
        System.out.println("1. 删除现有表...");

        // 删除交易记录表 - 使用独立操作
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS transaction_record");
            System.out.println("   ✓ transaction_record 表已删除");
        } catch (Exception e) {
            System.out.println("   ⚠ 删除 transaction_record 表失败: " + e.getMessage());
        }

        // 立即刷新连接，避免连接超时
        refreshConnection();

        // 删除账户表 - 使用独立操作
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS account");
            System.out.println("   ✓ account 表已删除");
        } catch (Exception e) {
            System.out.println("   ⚠ 删除 account 表失败: " + e.getMessage());
        }

        // 再次刷新连接
        refreshConnection();
    }

    /**
     * 创建所有表
     */
    private void createTables() {
        System.out.println("\n2. 创建表结构...");

        // 创建 account 表
        String createAccountTable =
                "CREATE TABLE account (" +
                        "id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID'," +
                        "account VARCHAR(255) NOT NULL COMMENT '帐号'," +
                        "password VARCHAR(255) NOT NULL COMMENT '密码'," +
                        "coupon INT NOT NULL DEFAULT 0 COMMENT '游戏点券'," +
                        "PRIMARY KEY (id)," +
                        "UNIQUE KEY uk_account (account)" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帐户表'";

        try {
            jdbcTemplate.execute(createAccountTable);
            System.out.println("   ✓ account 表创建成功");
        } catch (Exception e) {
            System.out.println("   ✗ account 表创建失败: " + e.getMessage());
            throw e;
        }

        // 刷新连接
        refreshConnection();

        // 创建 transaction_record 表
        String createTransactionTable =
                "CREATE TABLE transaction_record (" +
                        "id INT NOT NULL AUTO_INCREMENT COMMENT '主键ID'," +
                        "account VARCHAR(255) NOT NULL COMMENT '帐号'," +
                        "update_time DATETIME NOT NULL COMMENT '发生时间'," +
                        "category VARCHAR(10) NOT NULL COMMENT '类型：充值|消费'," +
                        "num INT NOT NULL COMMENT '数量'," +
                        "PRIMARY KEY (id)," +
                        "KEY idx_account (account)," +
                        "KEY idx_update_time (update_time)" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='充值及消费记录表'";

        try {
            jdbcTemplate.execute(createTransactionTable);
            System.out.println("   ✓ transaction_record 表创建成功");
        } catch (Exception e) {
            System.out.println("   ✗ transaction_record 表创建失败: " + e.getMessage());
            throw e;
        }
    }

    /**
     * 刷新连接：执行一个简单查询，获取新连接
     */
    private void refreshConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
        } catch (Exception e) {
            System.out.println("   ⚠ 刷新连接失败: " + e.getMessage());
        }
    }
}