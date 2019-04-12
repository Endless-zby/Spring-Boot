package com.zby.qa.service;

import com.zby.qa.dao.QuestionDao;
import com.zby.qa.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    //分页查询
    public Page<Question> findNewQuestionsByLabelId(String labelid,int start,int pageSize){
        PageRequest pagerequest = PageRequest.of(start - 1, pageSize);
        return questionDao.findNewQuestionsByLabelId(labelid,pagerequest);
    }

    //查询某个标签，根据热门问题排序
    public Page<Question>  findHotQuestionsByLabelId(String labelId ,int start,int pageSize){
        PageRequest pageRequest = PageRequest.of(start - 1, pageSize);
        return questionDao.findHotQuestionsByLabelId(labelId,pageRequest);
    }

    //查询某个标签，未回答的问题列表
    public Page<Question>  findWaitQuestionsByLabelId(String labelId ,int start,int pageSize){
        PageRequest pageRequest = PageRequest.of(start - 1, pageSize);
        return questionDao.findWaitQuestionsByLabelId(labelId,pageRequest);
    }

    //jpa规则下查找id
    public Question  findTop10ByIdEquals(String id){
        return questionDao.findTop10ByIdEquals(id);
    }
}
