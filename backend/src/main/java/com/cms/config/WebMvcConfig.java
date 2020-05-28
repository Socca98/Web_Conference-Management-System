package com.cms.config;

import com.cms.interceptor.AuthorizationInterceptor;
import com.cms.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    AuthorizationInterceptor requestInterceptor;
    @Autowired
    LoggingInterceptor loggingInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(requestInterceptor).excludePathPatterns("/login", "/register",
                "/conferences", "/init-database", "/files/download/**", "/error");
        super.addInterceptors(registry);
    }
}
