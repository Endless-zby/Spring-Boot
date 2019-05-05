package com.zby.active.controller;

import com.zby.active.enticle.Active;
import com.zby.active.service.ActiveService;
import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
@RequestMapping(value = "ActiveController")
public class ActiveController {

    @Autowired
    private ActiveService activeService;
    @Value("${zby.name}")
    private String name;

    //使用的redis
    @GetMapping("queryid/{id}")
    public Result queryid(@PathVariable String id){
        System.out.println(name);
        return new Result(true, StatusCode.OK,"查询成功！",activeService.queryid(id));
    }

    //使用的cache
    //查找
    @GetMapping("findActiveById/{id}")
    public Result findActiveById(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功！",activeService.findActiveById(id));
    }

    //增加和修改
    @PutMapping("addorUpdateActive")
    public Result addorUpdateActive(@RequestBody Active active){
        activeService.addorUpdateActive(active);
        return new Result(true, StatusCode.OK,"修改成功！",null);
    }

    //删除
    @DeleteMapping("deleteActiveById/{id}")
    public Result deleteActiveById(@PathVariable String id){
        activeService.deleteActiveById(id);
        return new Result(true, StatusCode.OK,"删除成功！",null);
    }
}
