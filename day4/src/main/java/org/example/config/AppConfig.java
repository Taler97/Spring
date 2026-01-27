package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.example")  // 扫描所有组件
public class AppConfig {
    // 不需要额外配置，依赖注解自动装配
}