package com.zby.elasticsearch.service;

import com.zby.elasticsearch.dao.ArticleESDao;
import com.zby.elasticsearch.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ArticleESService {

    @Autowired
    private ArticleESDao articleESDao ;


    public void saveArticle(Article article){
        articleESDao.save(article) ;
    }

    public Page<Article> findByTitleOrDescriptionLike(String keywords, int start, int pageSize ){
        PageRequest request = PageRequest.of(start, pageSize);
        return  articleESDao.findByTitleOrDescriptionLike(keywords,keywords,request);
    }
}
