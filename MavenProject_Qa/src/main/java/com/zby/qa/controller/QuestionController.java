package com.zby.qa.controller;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.qa.entity.Question;
import com.zby.qa.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


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
        return new Result(true,StatusCode.OK,"查询成功",questionService.findTop10ByIdEquals(id));
    }

    @GetMapping(value = "findByReplyTimeOrderById/{id}")
    public Result findByReplyTimeOrderById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",questionService.findAllByIdEquals(id));
    }
}
