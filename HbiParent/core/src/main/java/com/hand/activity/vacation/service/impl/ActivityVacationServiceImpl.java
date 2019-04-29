package com.hand.activity.vacation.service.impl;

import com.hand.activity.vacation.dto.ActivityVacation;
import com.hand.activity.vacation.service.ActivityVacationService;
import com.hand.hap.activiti.service.IActivitiService;
import com.hand.hap.core.IRequest;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityVacationServiceImpl implements ActivityVacationService {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IActivitiService activitiService;
    //启动流程
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startBill(IRequest iRequest, ActivityVacation demoVacation) {
        log.debug(demoVacation.getActivitiCode() + "====================的id是==" + demoVacation.getBusinessKey());
        ProcessInstanceCreateRequest instanceCreateRequest = new ProcessInstanceCreateRequest();
        instanceCreateRequest.setBusinessKey(demoVacation.getBusinessKey());//设置流程主键
        instanceCreateRequest.setProcessDefinitionKey(demoVacation.getActivitiCode());//设置流程编码
        instanceCreateRequest.setVariables(demoVacation.getVariable());
        activitiService.startProcess(iRequest, instanceCreateRequest);
    }
}