package com.zby.user;

import com.zby.util.IdWorker;


import com.zby.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);

    }

    //雪花算法
    @Bean //IdWorker放入ioc容器 （1. @Bean +返回值  2.三层注解 ）
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }


    //token
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }


}
