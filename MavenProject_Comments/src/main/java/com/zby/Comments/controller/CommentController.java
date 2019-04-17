package com.zby.Comments.controller;

import com.zby.Comments.entity.Comment;
import com.zby.Comments.service.CommentService;
import com.zby.entity.PageResult;
import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("CommentController")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private RedisTemplate redisTemplate;

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

    //点赞
    @PutMapping("likes/{id}")
    public Result updateLikes(@PathVariable String id){

        String userId="9527";//假定用户userId是9527

        //key:likes_用户id_文章id
        //value: 1 （1表示已经点过赞）
        if(redisTemplate.opsForValue().get("likes_"+userId+"_"+id) !=null){
            commentService.delUpdateLikes(id);
            redisTemplate.opsForValue().set("likes_"+userId+"_"+id,null);
            return new Result(true,StatusCode.OK,"取消点赞成功",null);
        }
        commentService.updateLikes(id);

        redisTemplate.opsForValue().set("likes_"+userId+"_"+id,"1");

        return new Result(true,StatusCode.OK,"点赞成功",null) ;
    }
}
