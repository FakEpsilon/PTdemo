package com.example.demosecond.dao.entity;

import java.io.Serializable;

public class AnswerEntity implements Serializable {
    private String id;
    private String questionId;
    private String singleOption;
    private String multipleOption;
    private String fillOption;

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

    public String getSingleOption() {
        return singleOption;
    }

    public void setSingleOption(String singleOption) {
        this.singleOption = singleOption;
    }

    public String getMultipleOption() {
        return multipleOption;
    }

    public void setMultipleOption(String multipleOption) {
        this.multipleOption = multipleOption;
    }

    public String getFillOption() {
        return fillOption;
    }

    public void setFillOption(String fillOption) {
        this.fillOption = fillOption;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id='" + id + '\'' +
                ", questionId='" + questionId + '\'' +
                ", singleOption='" + singleOption + '\'' +
                ", multipleOption='" + multipleOption + '\'' +
                ", fillOption='" + fillOption + '\'' +
                '}';
    }
}
