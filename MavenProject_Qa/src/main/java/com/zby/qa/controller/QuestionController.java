package com.zby.qa.controller;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.qa.entity.Question;
import com.zby.qa.service.QuestionService;
import com.zby.util.IdWorker;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(value = "question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private HttpServletRequest request;



    @GetMapping(value = "quesionlist/{labelid}/{start}/{pageSize}")
    public Result findNewQuestionsByLabelId(@PathVariable String labelid,@PathVariable int start,@PathVariable int pageSize){

        return new Result(true, StatusCode.OK,"查询成功",questionService.findNewQuestionsByLabelId(labelid,start,pageSize));

    }
    @GetMapping(value = "hotquesionlist/{labelid}/{start}/{pageSize}")
    public Result findHotQuestionsByLabelId(@PathVariable String labelid,@PathVariable int start,@PathVariable int pageSize){

        return new Result(true, StatusCode.OK,"查询成功",questionService.findHotQuestionsByLabelId(labelid,start,pageSize));

    }
    @GetMapping(value="waitquesionlist/{labelid}/{start}/{pagesize}")
    public Result findWaitQuestionsByLabelId(@PathVariable String labelid,@PathVariable int start,@PathVariable int pagesize){
        Page<Question> quesionsPage = questionService.findWaitQuestionsByLabelId(labelid, start, pagesize);
        return new Result(true, StatusCode.OK,"查询成功",quesionsPage  );
    }

    @GetMapping(value = "queryallbyid/{id}")
    public Result findTop10ByIdEquals(@PathVariable String id){
        Claims claims = (Claims)request.getAttribute("access");
        if(claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"失败,权限不足",null);
        }
        return new Result(true,StatusCode.OK,"查询成功",questionService.findTop10ByIdEquals(id));
    }

    @GetMapping(value = "findByReplyTimeOrderById/{id}")
    public Result findByReplyTimeOrderById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",questionService.findAllByIdEquals(id));
    }


    @PostMapping("add")
    public Result addQuestion(@RequestBody Question question){

        Claims claims = (Claims)request.getAttribute("access_admin");
        if(claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"发布失败，权限不足",null);
        }
        questionService.addQuestion(question);
        return new Result(true,StatusCode.OK,"添加成功",null);

    }


}
