package com.example.demosecond.dao.entity;

import java.io.Serializable;

public class QuestionEntity implements Serializable {
    private String id;
    private String questionnaireId;
    private String questionType;
    private String questionName;
    private String questionDescription;
    private String questionMa;
    private String questionSequence;

    public String getQuestionMa() {
        return questionMa;
    }

    public void setQuestionMa(String questionMa) {
        this.questionMa = questionMa;
    }

    public String getQuestionSequence() {
        return questionSequence;
    }

    public void setQuestionSequence(String questionSequence) {
        this.questionSequence = questionSequence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id='" + id + '\'' +
                ", questionnaireId='" + questionnaireId + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionName='" + questionName + '\'' +
                ", questionDescription='" + questionDescription + '\'' +
                ", questionMa='" + questionMa + '\'' +
                ", questionSequence='" + questionSequence + '\'' +
                '}';
    }
}
