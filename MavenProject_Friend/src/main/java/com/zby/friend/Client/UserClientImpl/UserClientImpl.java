package com.zby.friend.Client.UserClientImpl;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.friend.Client.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {

    public Result updataFansAdd(String userId) {
        System.out.println("熔断启动");
        return new Result(false, StatusCode.ERROR,"请求失败，服务器无响应,熔断机制启动保护",null);
    }


    public Result updataFansLes(String userId) {
        System.out.println("熔断启动");
        return new Result(false, StatusCode.ERROR,"请求失败，服务器无响应,熔断机制启动保护",null);
    }
}
