package com.zby.qa.dao;


import com.zby.qa.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface QuestionDao extends JpaRepository<Question,String>, JpaSpecificationExecutor<Question> {

        //自定义接口、sql语句（非规范接口命名）
        //JPQL （查询类，非表）
        //分页查询
        @Query(value="select q from Question q where id in(select questionId from QuestionLabel where labelId = ?1 ) order by replyTime desc")
        public Page<Question> findNewQuestionsByLabelId(String labelId , Pageable pageable) ;


        //查询某个标签，根据热门问题排序
        @Query(value="select q from Question q where id in (select questionId from QuestionLabel where labelId=?1) order by replies desc")
        public Page<Question> findHotQuestionsByLabelId(String labelId,Pageable pageable) ;


        //查询某个标签  未回答的问题列表
        //JPQL(面向对象SQL): @Query(value="select q from Question q where id in (select questionId from QuestionLabel where labelId=?1) and replies=0 order by pulishTime desc")
        //原生态SQL：nativeQuery=true
        @Query(nativeQuery=true,value="select * from tb_question q where id in (select question_id from tb_question_label where label_id=?) and replies=0 order by pulish_time desc")
        public Page<Question> findWaitQuestionsByLabelId(String labelId,Pageable pageable) ;
}
