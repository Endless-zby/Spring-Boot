package com.zby.elasticsearch.controller;

import com.zby.elasticsearch.entity.Article;
import com.zby.elasticsearch.service.ArticleESService;
import com.zby.entity.PageResult;
import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


@RefreshScope
@RestController
@RequestMapping("elasticsearch")
public class ArticleESController {

    @Autowired
    private ArticleESService articleESService ;
    @Autowired
    private RedisTemplate redisTemplate;

    //引擎添加词条
    @PostMapping
    public Result addArticle(@RequestBody Article article){
        articleESService.saveArticle(article);
        return new Result(true, StatusCode.OK,"存入引擎成功",null) ;
    }

    //搜索引擎
    @GetMapping("{keywords}/{start}/{pageSize}")
    public Result findByTitleOrDescriptionLike(@PathVariable String keywords,@PathVariable int start,@PathVariable("pageSize") int ps){
        Page<Article> articlePage = articleESService.findByTitleOrDescriptionLike(keywords, start-1, ps);
        return new Result(true, StatusCode.OK,"搜索成功" ,  new PageResult<Article>(articlePage.getTotalElements(),articlePage.getContent()  )) ;
    }

    //删除词条
    @DeleteMapping("{id}")
    public Result delArticle(@PathVariable String id){
        articleESService.delArticle(id);
        return new Result(true, StatusCode.OK,"删除词条成功",null) ;
    }

    //查询放缓存
    @GetMapping("get/{id}")
    public Result findbyid(@PathVariable String id){

        Article article = articleESService.findbyid(id);

        redisTemplate.opsForValue().set(id,article);

        return new Result(true, StatusCode.OK,"删除词条成功",article) ;
    }



}
