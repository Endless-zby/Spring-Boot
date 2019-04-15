package com.zby.article.service;

import com.zby.article.dao.ArticleDao;
import com.zby.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    //redis缓存
    @Autowired
    private RedisTemplate redisTemplate;

    //@Transactional如有异常自动回滚
    @Transactional
    public void reviewArticle(String id){
        articleDao.reviewArticle(id);
    }

    @Transactional
    public void updateLikes(String id){
        articleDao.updateLikes(id);
    }

    //redis缓存中存储一份
    @Transactional
    public Article queryid(String id){
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        //redis没有则在数据库查找，然后保存在redis中
        if(article == null){
            article = articleDao.findById(id).get();
            redisTemplate.opsForValue().set("article_" + id,article);
        }
        return article;
    }

}
