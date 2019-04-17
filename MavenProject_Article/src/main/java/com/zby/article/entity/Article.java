package com.zby.article.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_article")
public class Article implements Serializable {
    @Id
    private String id;
    private String columnId; //专栏
    private String userId;
    private String title;
    private String content;
    private String coverImage;//封面
    private Date publishTime;
    private int likes; //赞
    private String description;
    private Date updateTime;
    private String isPublic;//是否公开
    private String isTop;//是否顶置
    private int visits;
    private int comments; //评论
    private String state; //状态  ，未审核0  审核通过1
    private String url;
    private String type;

    public Article(String id, String columnId, String userId, String title, String content, String coverImage, Date publishTime, int likes, String description, Date updateTime, String isPublic, String isTop, int visits, int comments, String state, String url, String type) {
        this.id = id;
        this.columnId = columnId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.coverImage = coverImage;
        this.publishTime = publishTime;
        this.likes = likes;
        this.description = description;
        this.updateTime = updateTime;
        this.isPublic = isPublic;
        this.isTop = isTop;
        this.visits = visits;
        this.comments = comments;
        this.state = state;
        this.url = url;
        this.type = type;
    }
    public Article(String columnId, String userId, String title, String content, String coverImage, Date publishTime, int likes, String description, Date updateTime, String isPublic, String isTop, int visits, int comments, String state, String url, String type) {
        this.columnId = columnId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.coverImage = coverImage;
        this.publishTime = publishTime;
        this.likes = likes;
        this.description = description;
        this.updateTime = updateTime;
        this.isPublic = isPublic;
        this.isTop = isTop;
        this.visits = visits;
        this.comments = comments;
        this.state = state;
        this.url = url;
        this.type = type;
    }
    public Article() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}