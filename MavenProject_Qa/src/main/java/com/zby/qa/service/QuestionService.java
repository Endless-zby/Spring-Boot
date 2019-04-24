package com.zby.qa.service;

import com.zby.qa.dao.QuestionDao;
import com.zby.qa.entity.Question;
import com.zby.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private IdWorker idWorker;


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

    //jpa语法规则
    public Question findAllByIdEquals(String id){
        return questionDao.findAllByIdEquals(id);
    }

    /**
     * 提交问题,使用拦截器验证当前登录者的身份,
     *         如果是管理员（type = 1）的话，则允许发布，
     *          如果是用户（type = 0）的话，则不予发布
     * */

    public void addQuestion(Question question){
        //数据初始化
        question.setId(idWorker.nextId() + "");
        question.setPulishTime(new Date());
        question.setUpdateTime(new Date());
        question.setReplyTime(new Date());
        questionDao.save(question);
    }

}
