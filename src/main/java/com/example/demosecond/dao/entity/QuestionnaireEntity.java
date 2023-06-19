package com.example.demosecond.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class QuestionnaireEntity implements Serializable {
    private String id;
    private String userId;
    private String projectId;
    private String questionnaireName;
    private String questionnaireDescription;
    private Date startTime;
    private Date stopTime;
    private String createdBy;
    private Date creationDate;
    private Date issueDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;
    private String flag;
    private String orientedWho;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getQuestionnaireDescription() {
        return questionnaireDescription;
    }

    public void setQuestionnaireDescription(String questionnaireDescription) {
        this.questionnaireDescription = questionnaireDescription;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOrientedWho() {
        return orientedWho;
    }

    public void setOrientedWho(String orientedWho) {
        this.orientedWho = orientedWho;
    }

    @Override
    public String toString() {
        return "QuestionnaireEntity{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", questionnaireName='" + questionnaireName + '\'' +
                ", questionnaireDescription='" + questionnaireDescription + '\'' +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", issueDate=" + issueDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                ", flag='" + flag + '\'' +
                '}';
    }
}
