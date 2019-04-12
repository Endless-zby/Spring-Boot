package com.zby.qa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tb_question_label")
public class QuestionLabel  implements Serializable {
    @Id
    private String questionId ;
    @Id
    private String labelId ;

    public QuestionLabel() {
    }
    public QuestionLabel(String questionId, String labelId) {
        this.questionId = questionId;
        this.labelId = labelId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }
}
