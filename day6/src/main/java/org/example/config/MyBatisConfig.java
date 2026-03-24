package org.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan("org.example.service")
@EnableTransactionManagement
@MapperScan("org.example.mapper")
public class MyBatisConfig {

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        // 数据库连接配置
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/game_db?useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&connectTimeout=30000&socketTimeout=60000");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        // ========== 连接池大小配置 ==========
        dataSource.setInitialSize(2);
        dataSource.setMinIdle(2);
        dataSource.setMaxActive(10);
        dataSource.setMaxWait(60000);

        // ========== 关键：连接保活配置 ==========
        // 检测连接是否有效的SQL
        dataSource.setValidationQuery("SELECT 1");

        // 空闲时检测连接有效性
        dataSource.setTestWhileIdle(true);

        // 获取连接时检测连接有效性
        dataSource.setTestOnBorrow(true);

        // 归还连接时检测连接有效性
        dataSource.setTestOnReturn(false);

        // 配置间隔多久才进行一次检测（毫秒）
        dataSource.setTimeBetweenEvictionRunsMillis(30000);  // 30秒检测一次

        // 配置一个连接在池中最小生存的时间（毫秒）
        dataSource.setMinEvictableIdleTimeMillis(300000);    // 5分钟

        // 配置一个连接在池中最大生存的时间（毫秒）
        dataSource.setMaxEvictableIdleTimeMillis(600000);    // 10分钟

        // 连接泄漏检测
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(180);           // 3分钟
        dataSource.setLogAbandoned(true);

        // ========== 连接重试配置 ==========
        // 连接错误重试次数
        dataSource.setConnectionErrorRetryAttempts(3);

        // 获取连接失败后是否中断
        dataSource.setBreakAfterAcquireFailure(false);

        // ========== 其他配置 ==========
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        // 配置监控统计拦截的filters
        try {
            dataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 设置连接属性
        Properties properties = new Properties();
        properties.setProperty("useSSL", "false");
        properties.setProperty("characterEncoding", "utf8");
        properties.setProperty("serverTimezone", "Asia/Shanghai");
        dataSource.setConnectProperties(properties);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("org.example.entity");

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(true);
        sessionFactory.setConfiguration(configuration);

        return sessionFactory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}