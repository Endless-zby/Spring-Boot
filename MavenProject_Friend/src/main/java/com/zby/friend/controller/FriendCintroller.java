package com.zby.friend.controller;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("FriendCintroller")
public class FriendCintroller {

    @Autowired
    private FriendService friendService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("addfriend/{friendId}")
    public Result addfriend(@PathVariable("friendId") String friendId){
        Claims claims = (Claims)request.getAttribute("access_admin");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"未登录！",null);
        }
        int black = friendService.selectBlackList(claims.getId(), friendId);
        if(black == 1){
            return new Result(false, StatusCode.ERROR,"请先将该好友移除黑名单",null);
        }

        int addfriend = friendService.addfriend(claims.getId(), friendId);

        if(addfriend == 0){
            return new Result(false, StatusCode.ACCESSERROR,"不能加自己为好友",null);
        }
        if(addfriend == 1){
            return new Result(false, StatusCode.ACCESSERROR,"对方已经是你的好友，请勿重复添加",null);
        }
        return new Result(true,StatusCode.OK,"添加成功",null);
    }


    @PutMapping("makestar/{friendId}")
    public Result makeStar(@PathVariable("friendId") String friendId){
        Claims claims = (Claims)request.getAttribute("access_admin");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"未登录！",null);
        }
        // 1代表将好友关注
        // 判断当前好友是不是已经关注
        if(friendService.seletFriendIsStar(claims.getId(),friendId) == 0){
            friendService.makeStar(claims.getId(),friendId,"1");
            return new Result(true,StatusCode.OK,"设置关注成功",null);
        }else {
            friendService.makeStar(claims.getId(),friendId,"0");
            return new Result(true,StatusCode.OK,"取消关注成功",null);
        }
    }

    @PostMapping("addBlackList/{friendId}")
    public Result addBlackList(@PathVariable("friendId") String friendId){
        Claims claims = (Claims)request.getAttribute("access_admin");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"未登录！",null);
        }
        //先移出正常的好友列表，再加入到黑名单
        friendService.deleteFriend(claims.getId(), friendId);
        friendService.addBlackList(claims.getId(), friendId);

        return new Result(true,StatusCode.OK,"已加入黑名单",null);
    }

    @PostMapping("removeBlackList/{friendId}")
    public Result removeBlackList(@PathVariable("friendId") String friendId){
        Claims claims = (Claims)request.getAttribute("access_admin");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"未登录！",null);
        }
        friendService.removeBlackList(claims.getId(), friendId);
        return new Result(true,StatusCode.OK,"移除黑名单成功",null);
    }

    @PostMapping("removeBlackListAddToFriendList/{friendId}")
    public Result removeAddToFriend(@PathVariable("friendId") String friendId){
        Claims claims = (Claims)request.getAttribute("access_admin");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"未登录！",null);
        }
        friendService.removeBlackListAddToFriendList(claims.getId(), friendId);
        return new Result(true,StatusCode.OK,"已将该好友添加至好友列表",null);
    }

    @DeleteMapping("deleteFriend/{friendId}")
    public Result deleteFriend(@PathVariable("friendId") String friendId){
        Claims claims = (Claims)request.getAttribute("access_admin");
        if(claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"未登录！",null);
        }
        friendService.deleteFriend(claims.getId(), friendId);
        return new Result(true,StatusCode.OK,"删除成功",null);
    }


}
