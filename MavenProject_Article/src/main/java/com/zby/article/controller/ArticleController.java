package com.zby.article.controller;

import com.zby.article.service.ArticleService;
import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService ;
    @Value("${zby.name}")
    private String name;
    //get  put  delete  post
    @PutMapping("reviewArticle/{id}")
    public Result reviewArticle(@PathVariable String id){
        articleService.reviewArticle(id);
        return new Result(true, StatusCode.OK,"审核成功！",null);
    }

    @PutMapping("updateLikes/{id}")
    public Result updateLikes(@PathVariable String id){
        articleService.updateLikes(id);
        return new Result(true, StatusCode.OK,"点赞成功！",null);
    }

    @GetMapping("queryid/{id}")
    public Result queryid(@PathVariable String id){
        System.out.println(name);
        return new Result(true, StatusCode.OK,"查询成功！",articleService.queryid(id));
    }

}
