package com.zby.base.controller;

import com.zby.base.entity.Label;
import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.base.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.Map;

@RefreshScope
@RestController    //可以return值直接打印
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Value("${zby.name}")
    private String name;
    //查询所有get
    @GetMapping
    public Result findAll(){
        System.out.println(name);
        return new Result(true, StatusCode.OK,"查询成功", labelService.findAll() );
    }

    //增加post
    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK,"增加成功", labelService.findAll() );
    }

    //删除
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deletebyid(@PathVariable("id") String id){
        labelService.deletebyid(id);
        return new Result(true, StatusCode.OK,"删除成功", null);
    }

    //修改
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Label label,@PathVariable("id") String id){
        label.setId(id);
    labelService.update(label);
        return new Result(true, StatusCode.OK,"修改成功", null);
    }

    //查询ByID
    @RequestMapping(value = "{id}",method = RequestMethod.HEAD)
    public Result queryByid(@PathVariable("id") String id){
        return new Result(true, StatusCode.OK,"通过id值查询成功", labelService.queryByid(id));
    }
    //查询--->模糊查询/条件查询
    @RequestMapping(value = "findLabels",method = RequestMethod.POST)
    public Result queryBySpecification(@RequestBody Map queryMap){

        return new Result(true,StatusCode.OK,"条件查询成功",labelService.findLabels(queryMap));
    }

    //查询--->条件查询/分页
    @RequestMapping(value = "findLabels/{start}/{pagesize}",method = RequestMethod.GET)
    public Result queryByPage(@RequestBody Map queryMap,@PathVariable("start") int start,@PathVariable("pagesize") int pagesize){
        return new Result(true,StatusCode.OK,"条件查询成功",labelService.findLabels(queryMap,start,pagesize));
    }


}
