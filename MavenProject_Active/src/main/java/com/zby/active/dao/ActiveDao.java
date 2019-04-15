package com.zby.active.dao;

import com.zby.active.enticle.Active;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface ActiveDao extends JpaRepository<Active,String>, JpaSpecificationExecutor<Active> {


}