package com.zby.base;

import com.zby.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);

    }

    @Bean //IdWorker放入ioc容器 （1. @Bean +返回值  2.三层注解 ）
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
