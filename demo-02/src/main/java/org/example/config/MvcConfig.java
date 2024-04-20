package org.example.config;

import org.example.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddInitTransformer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springboot-demo
 * @description:
 * @author: David
 * @create: 2024-04-20 16:59
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor  myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/say/**");
    }
}
