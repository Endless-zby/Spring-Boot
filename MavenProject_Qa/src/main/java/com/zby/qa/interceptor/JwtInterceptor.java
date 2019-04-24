package com.zby.qa.interceptor;

import com.zby.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("此处拦截器已拦截，正在校验当前登录者的身份");
        //获取页面头Authrorization
        String authrorization = request.getHeader("Authrorization");

        if(authrorization != null && authrorization.startsWith("Bearer ")){
            //截取token段
            String token = authrorization.substring(7);
            //解析token
            Claims claims = jwtUtil.parseJwt(token);
            if(claims != null){
                if("1".equalsIgnoreCase((String) claims.get("roles"))){
                    request.setAttribute("access_admin",claims);
                }else if("0".equalsIgnoreCase((String) claims.get("roles"))){
                    request.setAttribute("access_error",claims);
                }else {
                    throw new RuntimeException("操作拒绝！");
                }
            }
            return true;
        }
        return true;
    }
}
