package com.zby.elasticsearch.dao;

import com.zby.elasticsearch.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleESDao  extends ElasticsearchRepository<Article,String> {

    //查询
    public Page<Article> findByTitleOrDescriptionLike(String title, String description, Pageable pageable);
}