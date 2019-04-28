package com.zby.active;

import com.zby.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableCaching //Spring Cache
@EnableEurekaClient
@SpringBootApplication
public class ActiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveApplication.class);

    }

    @Bean //IdWorker放入ioc容器 （1. @Bean +返回值  2.三层注解 ）
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
