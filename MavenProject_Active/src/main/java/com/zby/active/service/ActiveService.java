package com.zby.active.service;

import com.zby.active.dao.ActiveDao;
import com.zby.active.enticle.Active;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActiveService {

    @Autowired
    private ActiveDao activeDao;
    @Autowired
    private RedisTemplate redisTemplate;

    //redis缓存中存储一份
    public Active queryid(String id){
        Active active = (Active) redisTemplate.opsForValue().get("active_" + id);
        //redis没有则在数据库查找，然后保存在redis中
        if(active == null){
            active = activeDao.findById(id).get();
            redisTemplate.opsForValue().set("active_" + id,active);
        }
        return active;
    }

    //spring boot 全家桶自带的缓存cache

    /*
    查询： @Cacheable
    增删改：@CacheEvict
    */
    //查找
    @Transactional
    @Cacheable(key = "#id",cacheNames = "active")
    public Active findActiveById(String id){
        return activeDao.findById(id).get();
    }

    //增加|修改
    @Transactional
    @CacheEvict(key = "#active.id",cacheNames = "active")
    public void addorUpdateActive(Active active){
        activeDao.save(active);
    }

    //删除
    @Transactional
    @CacheEvict(key = "#id",cacheNames = "active")
    public void deleteActiveById(String id){
        activeDao.deleteById(id);
    }

}
