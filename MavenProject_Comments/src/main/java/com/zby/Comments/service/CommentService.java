package com.zby.Comments.service;

import com.zby.Comments.dao.CommentDao;
import com.zby.Comments.entity.Comment;
import com.zby.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    //分页查询byid
    public Page<Comment> findByParentId(String parentId,int start,int pageSize){
        PageRequest pagerequest = PageRequest.of(start-1, pageSize);
        return commentDao.findByParentId(parentId,pagerequest);
    }

    //查询byid
    public Comment findById(String id){
        return commentDao.findById(id).get();
    }

    //增加
    public void addComment(Comment comment){//aaa 100
        comment.set_id(  String.valueOf( idWorker.nextId())  );
        commentDao.save(comment) ;
    }

    //修改
    public void updateComment(Comment comment){
        commentDao.save(comment) ;
    }

    //删除
    public void deleteCommentById(String id){
        commentDao.deleteById(id);
    }

    //点赞
    public void updateLikes(String id){
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria) ;
        Update update = new Update();
        update.inc("likes",1);
        mongoTemplate.updateFirst( query,update,"Project_comment" )   ;
    }
    public void delUpdateLikes(String id){
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria) ;
        Update update = new Update();
        update.inc("likes",-1);
        mongoTemplate.updateFirst( query,update,"Project_comment" )   ;
    }
}
