package com.__4c_platform.platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 允许所有常见的前端开发地址
                .allowedOrigins(
                        "http://localhost:5173",  // Vite默认端口
                        "http://localhost:5174",  // 你的前端当前端口
                        "http://localhost:3000",  // Create React App默认端口
                        "http://localhost:8081"   // 其他可能的前端端口
                )
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}