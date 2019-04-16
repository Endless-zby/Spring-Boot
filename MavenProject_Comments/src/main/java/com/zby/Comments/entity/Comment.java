package com.zby.Comments.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;


public class Comment implements Serializable {
    @Id
    private String _id ;
    private String content ;
    private Date publishTime ;
    private String userId;
    private String nickname  ;
    private int visits ;
    private int likes ;
    private int shareCount ;//分享
    private int commentsCount ;//评论数量
    private String state ;//状态：关闭  开启
    private String parentId ;//父节点id

    public Comment() {
    }

    public Comment( String content, Date publishTime, String userId, String nickname, int visits, int likes, int shareCount, int commentsCount, String state, String parentId) {
        this.content = content;
        this.publishTime = publishTime;
        this.userId = userId;
        this.nickname = nickname;
        this.visits = visits;
        this.likes = likes;
        this.shareCount = shareCount;
        this.commentsCount = commentsCount;
        this.state = state;
        this.parentId = parentId;
    }



    public Comment(String _id, String content, Date publishTime, String userId, String nickname, int visits, int likes, int shareCount, int commentsCount, String state, String parentId) {
        this._id = _id;
        this.content = content;
        this.publishTime = publishTime;
        this.userId = userId;
        this.nickname = nickname;
        this.visits = visits;
        this.likes = likes;
        this.shareCount = shareCount;
        this.commentsCount = commentsCount;
        this.state = state;
        this.parentId = parentId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
