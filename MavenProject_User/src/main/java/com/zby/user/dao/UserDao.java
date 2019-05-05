package com.zby.user.dao;

import com.zby.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

        public User findByUsername(String username);

        public void deleteById(String id);

        @Query(nativeQuery = true,value = "update tb_user u SET u.fans = u.fans + ? where id = ?")
        @Modifying
        public void updatafans(int number, String userId );
}