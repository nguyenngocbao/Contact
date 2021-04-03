package com.safetrust.interview.contact.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.safetrust.interview.contact.intercertor.ExceptionIntercepter;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Autowired
    private ExceptionIntercepter exceptionIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(exceptionIntercepter).excludePathPatterns("/swagger-ui.html");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
