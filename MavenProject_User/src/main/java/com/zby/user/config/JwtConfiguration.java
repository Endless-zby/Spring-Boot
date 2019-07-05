package com.zby.user.config;


import com.zby.user.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

//配置拦截器
@Component
@Configuration
public class JwtConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * addInterceptor ：添加拦截方法
         * addPathPatterns ：添加拦截请求路径（/** ：拦截一切请求）
         * excludePathPatterns ：加入白名单（此请求不拦截）
         * */
        registry.addInterceptor(jwtInterceptor).addPathPatterns("")
                .excludePathPatterns(Arrays.asList("/**/login","/**/question/**","/**/index/**","/js/**"));//因为login之后才会生成token
    }

}
