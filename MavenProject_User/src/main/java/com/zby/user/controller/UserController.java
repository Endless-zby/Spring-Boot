package com.zby.user.controller;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.user.client.qaClient;
import com.zby.user.entity.User;
import com.zby.user.service.UserService;
import com.zby.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;




@RefreshScope
@Controller
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



    @RequestMapping("/index")
    public String first(){

        return "index";
    }

    @RequestMapping("/index2")
    public String two(){

        return "index2";
    }

    @RequestMapping("/index1")
    public ModelAndView last() {
        Result result = qaclient.findNewQuestionsByLabelId("1", 1, 4);
        System.out.println(result);
        return new ModelAndView("index1","result",result);
    }


    /**
     * 业务：验证操作加入队列
     * @param phone
     * @return
     */
    @ResponseBody
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
    @ResponseBody
    @PostMapping("adduser/{smscode}")
    public Result addUser(@RequestBody User user,@PathVariable String smscode){
        userService.addUser(user,smscode);
        return new Result(true,StatusCode.OK,"注册成功",null);
    }

    /**
     *
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("login")
    public Result login(@RequestBody User user){
        User login = userService.login(user);
        System.out.println(11);
        if(login == null){
            System.out.println();
            return new Result(false,StatusCode.ERROR,"账号或密码错误",null);
        }
        System.out.println(user.getUsername()+"------"+user.getPassword());
        String token = jwtUtil.creatJWT(login.getId(), login.getUsername(), String.valueOf(login.getType()));
        HashMap map = new HashMap();
        map.put("token",token);
        map.put("user",login);
//        response.setHeader("Authrorization","Bearer "+token);
        return new Result(true,StatusCode.OK,"登录成功",map);
    }

    /**
     * 业务：删除用户
     * @param id
     * @return
     */
    @ResponseBody
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
    @ResponseBody
    @GetMapping("updataFansAdd1/{userId}")
    public Result updataFansAdd1(@PathVariable String userId){
        Claims claims = (Claims)request.getAttribute("admin_claims");
        if(claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"权限不足",null);
        }
        userService.updataFans(userId,1);
        return new Result(true,StatusCode.OK,"粉丝增加1",null);
    }

    @ResponseBody
    @GetMapping("updataFansLessen1/{userId}")
    public Result updataFansLessen1(@PathVariable String userId){
        userService.updataFans(userId,-1);
        return new Result(true,StatusCode.OK,"粉丝减少1",null);
    }

    /**
     * 跨服务调用Qa中提交问题
     */
    @ResponseBody
    @GetMapping(value="/question/quesionlist/{labelid}/{start}/{pageSize}")
    public Result findNewQuestionsByLabelId(@PathVariable("labelid") String labelid, @PathVariable("start") int start, @PathVariable("pageSize") int pageSize){
        return qaclient.findNewQuestionsByLabelId(labelid,start,pageSize);
    }

    @ResponseBody
    @GetMapping(value = "/question/hotquesionlist/{labelid}/{start}/{pageSize}")
    public Result findHotQuestionsByLabelId(@PathVariable("labelid") String labelid,@PathVariable("start") int start,@PathVariable("pageSize") int pageSize){
        return qaclient.findHotQuestionsByLabelId(labelid,start,pageSize);
    }

    @ResponseBody
    @GetMapping(value="/question/waitquesionlist/{labelid}/{start}/{pagesize}")
    public Result findWaitQuestionsByLabelId(@PathVariable("labelid") String labelid,@PathVariable("start") int start,@PathVariable("pageSize") int pagesize){
        return qaclient.findWaitQuestionsByLabelId(labelid,start,pagesize);
    }

    @ResponseBody
    @GetMapping(value = "/question/queryallbyid/{id}")
    public Result findTop10ByIdEquals(@PathVariable("id") String id){
        return qaclient.findTop10ByIdEquals(id);
    }

    @ResponseBody
    @GetMapping(value = "/question/findByReplyTimeOrderById/{id}")
    public Result findByReplyTimeOrderById(@PathVariable("id") String id){
        return qaclient.findByReplyTimeOrderById(id);
    }

    /**
     * 跨服务调用Friend中功能
     */



}
