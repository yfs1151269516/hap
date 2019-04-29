package com.hand.activity.webservice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CallProductVoo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long activityId;
    private String eventName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseEndDate;
    private String createdByBy;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private String activeStatus;
    private String activities;
    private String privilege;
    private String managingEmployees;
    private double activityAmount;

    private List<String> RuleName;

    private List<String> ClientName;

    public List<String> getRuleName() {
        return RuleName;
    }

    public void setRuleName(List<String> ruleName) {
        RuleName = ruleName;
    }

    public List<String> getClientName() {
        return ClientName;
    }

    public void setClientName(List<String> clientName) {
        ClientName = clientName;
    }

    private String tushi;

    public String getTushi() {
        return tushi;
    }

    public void setTushi(String tushi) {
        this.tushi = tushi;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getReleaseEndDate() {
        return releaseEndDate;
    }

    public void setReleaseEndDate(Date releaseEndDate) {
        this.releaseEndDate = releaseEndDate;
    }

    public String getCreatedByBy() {
        return createdByBy;
    }

    public void setCreatedByBy(String createdByBy) {
        this.createdByBy = createdByBy;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getManagingEmployees() {
        return managingEmployees;
    }

    public void setManagingEmployees(String managingEmployees) {
        this.managingEmployees = managingEmployees;
    }

    public double getActivityAmount() {
        return activityAmount;
    }

    public void setActivityAmount(double activityAmount) {
        this.activityAmount = activityAmount;
    }
}
