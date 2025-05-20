package com.project.dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Or use allowedOriginPatterns for flexibility
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Explicit methods
                .allowedHeaders("*")
                .allowCredentials(true); // If using cookies/auth
    }
}
