package com.zby.friend.dao;

import com.zby.friend.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BlackListDao extends JpaRepository<BlackList,String>, JpaSpecificationExecutor<BlackList> {

    @Query(nativeQuery = true,value = "select count(*) from tb_blacklist where user_Id = ? and friend_Id = ?")
    int selectBlackList(String userId,String friendId);


}
