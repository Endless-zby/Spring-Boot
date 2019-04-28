package com.zby.friend.service;

import com.zby.friend.dao.BlackListDao;
import com.zby.friend.dao.FriendDao;
import com.zby.friend.entity.BlackList;
import com.zby.friend.entity.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private BlackListDao blackListDao;

    /**
     * 业务：添加好友
     * 新增:返回值 0：不能自己加自己   1:不能重复添加   2：成功
     * @param userId
     * @param friendId
     * @return
     */
    public int addfriend(String userId,String friendId){
        Friend friend = new Friend(userId,friendId,"0");
        if(userId.equals(friendId)){
            return 0;
        }
        if(friendDao.selectIsFriend(userId, friendId) > 0){
            return 1;
        }
            friendDao.save(friend);
        return 2;
    }

    /**
     * 业务：加为关注好友或者取消关注
     * @param userId
     * @param friendId
     * @param isStar
     */
    public void makeStar(String userId,String friendId,String isStar){
        friendDao.updateIsStar(userId,friendId,isStar);
    }

    /**
     * 业务：查询好友目前是不是已经关注
     * @param userId
     * @param friendId
     * @return
     */
    public int seletFriendIsStar(String userId,String friendId){
        return friendDao.seletFriendIsStar(userId, friendId)>0 ? 1 : 0;
    }

    public int selectBlackList(String userId,String friendId){
        return blackListDao.selectBlackList(userId,friendId);
    }

    /**
     * 业务：删除当前好友
     * @param userId
     * @param friendId
     */
    public void deleteFriend(String userId,String friendId){
        friendDao.deleteByUserIdAndFriendId(userId,friendId);
    }

    /**
     * 业务：添加当前好友至黑名单
     * @param userId
     * @param friendId
     * @return
     */
    public void addBlackList(String userId,String friendId){
        BlackList blackList = new BlackList(userId, friendId);
        blackListDao.save(blackList);
    }

    /**
     * 业务：移除黑名单（删除该好友）
     * @param userId
     * @param friendId
     */
    public void removeBlackList(String userId,String friendId){
        BlackList blackList = new BlackList(userId, friendId);
        blackListDao.delete(blackList);
    }

    /**
     * 业务：移除黑名单并添加至好友列表
     * @param userId
     * @param friendId
     */
    public void removeBlackListAddToFriendList(String userId,String friendId){
        BlackList blackList = new BlackList(userId, friendId);
        blackListDao.delete(blackList);
        Friend friend = new Friend(userId, friendId, "0");
        friendDao.save(friend);
    }

}
