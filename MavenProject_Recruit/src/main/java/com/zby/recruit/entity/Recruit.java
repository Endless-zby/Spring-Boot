package com.zby.recruit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_recruit")
public class Recruit implements Serializable {
    @Id
    private int id ;
    private String jobName ;
    private int salary ;
    private String  requirement;//条件
    private String education ;//学历
    private String cid ;//公司id
    private String label ;
    private String content ;//招聘正文

    public Recruit(int id, String jobName, int salary, String requirement, String education, String cid, String label, String content) {
        this.id = id;
        this.jobName = jobName;
        this.salary = salary;
        this.requirement = requirement;
        this.education = education;
        this.cid = cid;
        this.label = label;
        this.content = content;
    }

    public Recruit() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
