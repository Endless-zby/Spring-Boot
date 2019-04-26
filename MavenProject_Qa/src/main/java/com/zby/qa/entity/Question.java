package com.zby.qa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
/*
    问题 ：  标签

    1标签（旅游）： n问题
    1问题（最便宜） ：  n标签

    n:m

 */
@Entity
@Table(name="tb_question")
public class Question implements Serializable {
    @Id
    private String id ;
    private String title ;
    private String content ;
    private Date pulishTime ;
    private Date updateTime ;
    private String userId ;
    private String username ; //nickname
    private int visits ;//浏览量
    private int replies ;//回复量
    private String sovle ;
    private String replyname ;
    private Date replyTime ;
    public Question( ) {
    }
    public Question(String id, String title, String content, Date pulishTime, Date updateTime, String userId, String username, int visits, int replies, String sovle, String replyname, Date replyTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.pulishTime = pulishTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.username = username;
        this.visits = visits;
        this.replies = replies;
        this.sovle = sovle;
        this.replyname = replyname;
        this.replyTime = replyTime;
    }

    public Question( String title, String content, Date pulishTime, Date updateTime, String userId, String username, int visits, int replies, String sovle, String replyname, Date replyTime) {

        this.title = title;
        this.content = content;
        this.pulishTime = pulishTime;
        this.updateTime = updateTime;
        this.userId = userId;
        this.username = username;
        this.visits = visits;
        this.replies = replies;
        this.sovle = sovle;
        this.replyname = replyname;
        this.replyTime = replyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPulishTime() {
        return pulishTime;
    }

    public void setPulishTime(Date pulishTime) {
        this.pulishTime = pulishTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public String getSovle() {
        return sovle;
    }

    public void setSovle(String sovle) {
        this.sovle = sovle;
    }

    public String getReplyname() {
        return replyname;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
}
