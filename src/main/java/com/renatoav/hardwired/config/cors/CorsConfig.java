package com.renatoav.hardwired.config.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private static String[] allowedOrigins;
    private static final String[] allowedHeaders = new String[]{"Authorization", "content-type"};
    private static final String[] allowedMethods = new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"};

    @Value("${cors.allowed-origins}")
    public void setAllowedOrigins(String[] allowedOrigins) {
        CorsConfig.allowedOrigins = allowedOrigins;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedHeaders(allowedHeaders)
                .allowedMethods(allowedMethods);
    }
}
