package com.zby.article.service;

import com.zby.article.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Transactional
    public void reviewArticle(String id){
        articleDao.reviewArticle(id);
    }

    @Transactional
    public void updateLikes(String id){
        articleDao.updateLikes(id);
    }
}
