package com.zby.user.service;

import com.zby.user.dao.UserDao;
import com.zby.user.entity.User;
import com.zby.util.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;


@Service
public class UserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private BCryptPasswordEncoder encoder ;

    //验证操作加入队列
    @Transactional
    public void sendSms(String phone){
        //随机验证码
        String smsCode = ((int)(Math.random()*9000)+1000) + "";
        //储存redis
        redisTemplate.opsForValue().set("sms_" + phone,smsCode);
        //数据整理
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("smscode",smsCode);
        //加入队列
        rabbitTemplate.convertAndSend("sms",map);
    }

    //模拟用户输入验证码并完成注册操作
    /*
    参数：
            User(username,password)
            smscode
     */
    @Transactional
    public void addUser(User user, String smscode){
        //redis中获取以保存的验证码
        String redisCode = (String) redisTemplate.opsForValue().get("sms_" + user.getPhone());
        //数据校验
        if(!smscode.equals(redisCode)){
            throw new RuntimeException("验证码错误") ;
        }
        //初始化用户数据
        user.setId(idWorker.nextId() + "");
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        user.setFans(0);
        user.setRegisterTime(new Date());
        user.setLastLoginTime(new Date());
        user.setUpdateTime(new Date());
        userDao.save(user);
    }

    //登录

    public User login(User user){
        //数据库查询
        User users = userDao.findByUsername(user.getUsername());
        //密码解密验证
        if(users != null && encoder.matches(user.getPassword(),users.getPassword())){
            return users;
        }
        return null;
    }
    //删除
    @Transactional
    public void deleteById(String id){
        userDao.deleteById(id);
    }

    //粉丝加1
    @Transactional
    public void updataFans(String userId,int number){
        userDao.updatafans(number,userId);
    }

}
