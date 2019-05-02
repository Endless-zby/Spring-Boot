package com.zby.adminzuul;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy    //开启网关
@SpringBootApplication
public class AdminZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminZuulApplication.class);

    }
}
