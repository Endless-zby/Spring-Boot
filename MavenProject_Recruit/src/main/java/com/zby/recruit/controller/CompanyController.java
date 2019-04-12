package com.zby.recruit.controller;

import com.zby.entity.Result;
import com.zby.entity.StatusCode;
import com.zby.recruit.servive.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    //查找热门
    @GetMapping(value = "popularList/{isPopular}")
    public Result popularList(@PathVariable("isPopular") String isPopular){

        return new Result(true, StatusCode.OK,"查询成功",companyService.popularList(isPopular));
    }
}
