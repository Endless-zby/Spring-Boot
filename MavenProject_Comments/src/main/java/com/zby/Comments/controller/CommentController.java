package com.zby.Comments.controller;

import com.zby.Comments.entity.Comment;
import com.zby.Comments.service.CommentService;
import com.zby.entity.PageResult;
import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("CommentController")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //分页查询
    @GetMapping("{parentId}/{start}/{pageSize}")
    public Result findByParentId(@PathVariable String parentId,@PathVariable int start,@PathVariable int pageSize){

        Page<Comment> page = commentService.findByParentId(parentId, start, pageSize);

        PageResult<Comment> paPageResult = new PageResult<>(page.getTotalElements(), page.getContent());

        return new Result(true, StatusCode.OK,"查询成功",paPageResult);
    }

    //id查询
    @GetMapping("{id}")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",commentService.findById(id));
    }

    //增加
    @PostMapping
    public Result addComment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return new Result(true,StatusCode.OK,"增加成功",null) ;
    }

    //修改
    @PutMapping("{id}")
    public Result updateComment(@RequestBody Comment comment ,@PathVariable String id){
        comment.set_id(id);
        commentService.updateComment(comment);
        return new Result(true,StatusCode.OK,"修改成功",null) ;
    }

    //删除
    @DeleteMapping("{id}")
    public Result deleteCommentById(@PathVariable String id){
        commentService.deleteCommentById(id);
        return new Result(true,StatusCode.OK,"删除成功",null) ;
    }
}
