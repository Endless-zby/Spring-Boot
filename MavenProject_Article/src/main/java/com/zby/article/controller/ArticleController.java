package com.zby.article.controller;

import com.zby.article.service.ArticleService;
import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService ;
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
        return new Result(true, StatusCode.OK,"查询成功！",articleService.queryid(id));
    }

}
