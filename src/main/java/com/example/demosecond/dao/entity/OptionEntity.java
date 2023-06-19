package com.example.demosecond.dao.entity;

import java.io.Serializable;

public class OptionEntity implements Serializable {
    private String id;
    private String questionId;
    private String optionName;
    private String optionFraction;
    private String optionSequence;

    public String getOptionSequence() {
        return optionSequence;
    }

    public void setOptionSequence(String optionSequence) {
        this.optionSequence = optionSequence;
    }

    public String getOptionFraction() {
        return optionFraction;
    }

    public void setOptionFraction(String optionFraction) {
        this.optionFraction = optionFraction;
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

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }


    @Override
    public String toString() {
        return "OptionEntity{" +
                "id='" + id + '\'' +
                ", questionId='" + questionId + '\'' +
                ", optionName='" + optionName + '\'' +
                ", optionFraction='" + optionFraction + '\'' +
                ", optionSequence='" + optionSequence + '\'' +
                '}';
    }
}
