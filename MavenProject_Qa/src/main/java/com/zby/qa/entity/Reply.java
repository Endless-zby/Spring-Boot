package com.zby.qa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tb_reply")
public class Reply implements Serializable {
    @Id
    private String id ;
    private String questionId ;
    private String content ;
    private Date answerTime ;
    private Date updateTime ;
    private String userid ;
    private String username ;

    public Reply(String id, String questionId, String content, Date answerTime, Date updateTime, String userid, String username) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.answerTime = answerTime;
        this.updateTime = updateTime;
        this.userid = userid;
        this.username = username;
    }
    public Reply() {
    }
    public Reply(String questionId, String content, Date answerTime, Date updateTime, String userid, String username) {

        this.questionId = questionId;
        this.content = content;
        this.answerTime = answerTime;
        this.updateTime = updateTime;
        this.userid = userid;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
