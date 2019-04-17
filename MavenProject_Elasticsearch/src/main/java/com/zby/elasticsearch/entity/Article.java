package com.zby.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Document(indexName = "articleindex",type = "article")
public class Article implements Serializable {
    @Id
    private String id ;

    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private String title ;

    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private String description ;//描述，搜索时展现的概述（文章描述信息，用于搜索时展示）

    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private String content ;//全部的文章内容

    private String state ;//状态
    public Article( ) {
    }
    public Article(String title, String description, String content, String state) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.state = state;
    }
    public Article(String id, String title, String description, String content, String state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.state = state;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
