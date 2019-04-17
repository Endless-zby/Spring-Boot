package com.zby.elasticsearch.controller;

import com.zby.elasticsearch.entity.Article;
import com.zby.elasticsearch.service.ArticleESService;
import com.zby.entity.PageResult;
import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("elasticsearch")
public class ArticleESController {

    @Autowired
    private ArticleESService articleESService ;

    @PostMapping
    public Result addArticle(@RequestBody Article article){
        articleESService.saveArticle(article);
        return new Result(true, StatusCode.OK,"存入引擎成功",null) ;
    }

    @GetMapping("{keywords}/{start}/{pageSize}")
    public Result findByTitleOrDescriptionLike(@PathVariable String keywords,@PathVariable int start,@PathVariable("pageSize") int ps){
        Page<Article> articlePage = articleESService.findByTitleOrDescriptionLike(keywords, start-1, ps);
        return new Result(true, StatusCode.OK,"搜索成功" ,  new PageResult<Article>(articlePage.getTotalElements(),articlePage.getContent()  )) ;
    }
}
