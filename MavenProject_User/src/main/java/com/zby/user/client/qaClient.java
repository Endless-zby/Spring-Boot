package com.zby.user.client;


import com.zby.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient("MavenProject-Qa")
public interface qaClient {

    @GetMapping(value = "question/quesionlist/{labelid}/{start}/{pageSize}")
    Result findNewQuestionsByLabelId(@PathVariable("labelid") String labelid, @PathVariable("start") int start, @PathVariable("pageSize") int pageSize);

    @GetMapping(value = "question/hotquesionlist/{labelid}/{start}/{pageSize}")
    Result findHotQuestionsByLabelId(@PathVariable("labelid") String labelid,@PathVariable("start") int start,@PathVariable("pageSize") int pageSize);

    @GetMapping(value="question/waitquesionlist/{labelid}/{start}/{pagesize}")
    Result findWaitQuestionsByLabelId(@PathVariable("labelid") String labelid,@PathVariable("start") int start,@PathVariable("pageSize") int pagesize);

    @GetMapping(value = "question/queryallbyid/{id}")
    Result findTop10ByIdEquals(@PathVariable("id") String id);

    @GetMapping(value = "question/findByReplyTimeOrderById/{id}")
    Result findByReplyTimeOrderById(@PathVariable("id") String id);

//    @PostMapping("/question/add")
//    public Result addQuestion(@RequestBody TypePatternQuestions.Question question);
}
