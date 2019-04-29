package com.hand.activity.vacation.dto;


import org.activiti.rest.service.api.engine.variable.RestVariable;

import java.util.List;

public class ActivityVacation {



    private String activitiCode;//流程编码
    private String businessKey;//业务-编码
    private List<RestVariable> variable;//流程变量


    public String getActivitiCode() {
        return activitiCode;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public List<RestVariable> getVariable() {
        return variable;
    }

    public void setActivitiCode(String activitiCode) {
        this.activitiCode = activitiCode;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public void setVariable(List<RestVariable> variable) {
        this.variable = variable;
    }


     }
