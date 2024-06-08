package com.headline.config;

import com.headline.interceptor.LoginProtectInterceptor;
import com.headline.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-06-07 22:28
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Autowired
    private LoginProtectInterceptor loginProtectInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/user/**");
        registry.addInterceptor(loginProtectInterceptor).addPathPatterns("/headline/**");
    }
}
