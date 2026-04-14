package org.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ResourceBundle;

@Configuration
@ComponentScan("org.example.service")
public class AppConfig {





    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();

        // 数据库连接配置
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springmvcTest?useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&connectTimeout=30000&socketTimeout=60000");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        // ========== 连接池大小配置 ==========
        dataSource.setInitialSize(2);
        dataSource.setMinIdle(2);
        dataSource.setMaxActive(10);
        dataSource.setMaxWait(60000);

        return dataSource;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("org.example.entity");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));  // ← 路径是否正确
        return factoryBean;
    }
    // Mapper 扫描
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scanner = new MapperScannerConfigurer();
        scanner.setBasePackage("org.example.mapper");  // ← 扫描 mapper 包
        scanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return scanner;
    }

    // 事务管理器（需要补充）
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
