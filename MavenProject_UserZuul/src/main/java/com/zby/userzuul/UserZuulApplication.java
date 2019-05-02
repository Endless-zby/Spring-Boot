package com.zby.userzuul;


import com.zby.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@EnableZuulProxy    //开启网关
@SpringBootApplication
public class UserZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserZuulApplication.class);

    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
