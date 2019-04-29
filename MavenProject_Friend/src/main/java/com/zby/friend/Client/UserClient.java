package com.zby.friend.Client;

import com.zby.entity.Result;
import com.zby.friend.Client.UserClientImpl.UserClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@FeignClient(value = "MavenProject-User",fallback = UserClientImpl.class)
public interface UserClient {

    //a在关注b后应该给b的粉丝加1
    @GetMapping("UserController/updataFansAdd1/{userId}")
    public Result updataFansAdd(@PathVariable("userId") String userId);
    @GetMapping("UserController/updataFansLessen1/{userId}")
    public Result updataFansLes(@PathVariable("userId") String userId);

}
