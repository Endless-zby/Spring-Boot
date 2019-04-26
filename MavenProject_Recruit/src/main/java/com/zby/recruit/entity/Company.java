package com.zby.recruit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_company")
public class Company implements Serializable {
    @Id
    private String id ;
    private String name ;
    private String brief ;
    private String address ;
    private String labels ;
    private String isPopular ;//1：热门     0:非热门
    private String logo ;
    private int jobCount ;
    private String url ;

    public Company(String id, String name, String brief, String address, String labels, String isPopular, String logo, int jobCount, String url) {
        this.id = id;
        this.name = name;
        this.brief = brief;
        this.address = address;
        this.labels = labels;
        this.isPopular = isPopular;
        this.logo = logo;
        this.jobCount = jobCount;
        this.url = url;
    }

    public Company() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(String isPopular) {
        this.isPopular = isPopular;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




}
