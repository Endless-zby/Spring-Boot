package com.zby.user.controller;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.user.client.qaClient;
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
    @Autowired
    private qaClient qaclient;


    /**
     * 业务：验证操作加入队列
     * @param phone
     * @return
     */
    @PostMapping("{phone}")
    public Result sendSms(@PathVariable String phone){
        userService.sendSms(phone);
        return new Result(true, StatusCode.OK,"加入rabbit队列成功",null);
    }

    /**
     * 业务：队列操作执行后保存用户数据
     * @param user
     * @param smscode
     * @return
     */

    @PostMapping("adduser/{smscode}")
    public Result addUser(@RequestBody User user,@PathVariable String smscode){
        userService.addUser(user,smscode);
        return new Result(true,StatusCode.OK,"注册成功",null);
    }

    /**
     * 业务：用户登录
     * @param user
     * @return
     */
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

    /**
     * 业务：删除用户
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable String id){

        Claims claims = (Claims)request.getAttribute("admin_claims");
        if(claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"权限不足",null);
        }
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功",null);
    }

    /**
     * 业务：更新粉丝数lessen
     * @param userId
     * @return
     */
    @GetMapping("updataFansAdd1/{userId}")
    public Result updataFansAdd1(@PathVariable String userId){
        userService.updataFans(userId,1);
        return new Result(true,StatusCode.OK,"粉丝增加1",null);
    }
    @GetMapping("updataFansLessen1/{userId}")
    public Result updataFansLessen1(@PathVariable String userId){
        userService.updataFans(userId,-1);
        return new Result(true,StatusCode.OK,"粉丝减少1",null);
    }

    /**
     * 跨服务调用Qa中提交问题
     */
    @GetMapping(value="/question/quesionlist/{labelid}/{start}/{pageSize}")
    public Result findNewQuestionsByLabelId(@PathVariable("labelid") String labelid, @PathVariable("start") int start, @PathVariable("pageSize") int pageSize){
        return qaclient.findNewQuestionsByLabelId(labelid,start,pageSize);
    }

    @GetMapping(value = "/question/hotquesionlist/{labelid}/{start}/{pageSize}")
    public Result findHotQuestionsByLabelId(@PathVariable("labelid") String labelid,@PathVariable("start") int start,@PathVariable("pageSize") int pageSize){
        return qaclient.findHotQuestionsByLabelId(labelid,start,pageSize);
    }

    @GetMapping(value="/question/waitquesionlist/{labelid}/{start}/{pagesize}")
    public Result findWaitQuestionsByLabelId(@PathVariable("labelid") String labelid,@PathVariable("start") int start,@PathVariable("pageSize") int pagesize){
        return qaclient.findWaitQuestionsByLabelId(labelid,start,pagesize);
    }

    @GetMapping(value = "/question/queryallbyid/{id}")
    public Result findTop10ByIdEquals(@PathVariable("id") String id){
        return qaclient.findTop10ByIdEquals(id);
    }

    @GetMapping(value = "/question/findByReplyTimeOrderById/{id}")
    public Result findByReplyTimeOrderById(@PathVariable("id") String id){
        return qaclient.findByReplyTimeOrderById(id);
    }

    /**
     * 跨服务调用Friend中功能
     */






}
