package com.zby.user.dao;



import com.zby.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

public User findByUsername(String username);
public void deleteById(String id);
}