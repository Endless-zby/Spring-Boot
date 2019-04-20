package com.zby.user.controller;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.user.entity.User;
import com.zby.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("UserController")
public class UserController {

    @Autowired
    private UserService userService;

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
}
