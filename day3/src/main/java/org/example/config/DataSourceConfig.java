package org.example.config; // 替换为你自己的包名

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = {"org/example/service", "org/example/dao", "org/example/bean"})
public class DataSourceConfig {

    @Bean(name = "dataSource")
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        // 连接池配置
        dataSource.setInitialSize(5); // 初始化时建立的连接数
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);//最大存活数
        dataSource.setMaxWait(60000); // 等待获取连接的最大时间（毫秒）
        dataSource.setTimeBetweenEvictionRunsMillis(60000); // 检查空闲连接的时间间隔
        dataSource.setMinEvictableIdleTimeMillis(300000); // 最小空闲时间，超过该时间的连接将被回收
        dataSource.setValidationQuery("SELECT 1 FROM DUAL"); // 用于验证连接是否有效的查询语句
        dataSource.setTestWhileIdle(true); // 建立检查连接有效性
        dataSource.setTestOnBorrow(false); // 从连接池借出连接前不测试
        dataSource.setTestOnReturn(false); // 归还连接后不测试

        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
