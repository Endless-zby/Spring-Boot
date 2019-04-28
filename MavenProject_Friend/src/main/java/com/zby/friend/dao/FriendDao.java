package com.zby.friend.dao;

import com.zby.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface FriendDao extends JpaRepository<Friend,String>,JpaSpecificationExecutor<Friend> {

    @Query(nativeQuery = true,value = "select count(*) from tb_friend where user_Id = ? and friend_Id = ?")
    public int selectIsFriend(String userId,String friendId);

    @Modifying
    @Query("update Friend f set f.isStar = ?3 where f.userId = ?1 and f.friendId = ?2")
    public void updateIsStar(String userId,String friendId,String isStar) ;

    @Query(nativeQuery = true,value = "select count(*) from tb_friend where user_Id = ? and friend_Id = ? and is_Star = 1")
    public int seletFriendIsStar(String userId,String friendId);

    public void deleteByUserIdAndFriendId(String userId,String friendId);
}
