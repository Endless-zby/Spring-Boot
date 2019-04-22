package com.zby.user.controller;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.user.entity.User;
import com.zby.user.service.UserService;
import com.zby.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@RequestMapping("UserController")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;

    //验证操作加入队列
    @PostMapping("{phone}")
    public Result sendSms(@PathVariable String phone){

        userService.sendSms(phone);

        return new Result(true, StatusCode.OK,"加入rabbit队列成功",null);
    }

    //队列操作执行后保存用户数据
    @PostMapping("adduser/{smscode}")
    public Result addUser(@RequestBody User user,@PathVariable String smscode){

        userService.addUser(user,smscode);

        return new Result(true,StatusCode.OK,"注册成功",null);
    }

    //登录
    @GetMapping("login")
    public Result login(@RequestBody User user){

        User login = userService.login(user);
        if(login == null){
            return new Result(false,StatusCode.ERROR,"账号或密码错误",null);
        }
        String token = jwtUtil.creatJWT(login.getId(), login.getUsername(), String.valueOf(login.getType()));
        HashMap map = new HashMap();
        map.put("token",token);
        map.put("user",login);
        return new Result(true,StatusCode.OK,"登录成功",map);
    }

    //删除
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable String id){

        Claims claims = (Claims)request.getAttribute("admin_claims");
        if(claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"权限不足",null);
        }
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功",null);


    }
}
