package com.hand.vacation.service.impl;

import com.hand.hap.activiti.service.IActivitiService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceCreateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.vacation.dto.Demo1Vacation;
import com.hand.vacation.service.IDemo1VacationService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Demo1VacationServiceImpl implements IDemo1VacationService {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IActivitiService activitiService;
    //启动流程
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startBill(IRequest iRequest, Demo1Vacation demoVacation) {
        log.debug(demoVacation.getActivitiCode() + "====================的id是==" + demoVacation.getBusinessKey());
        ProcessInstanceCreateRequest instanceCreateRequest = new ProcessInstanceCreateRequest();
        instanceCreateRequest.setBusinessKey(demoVacation.getBusinessKey());//设置流程主键
        instanceCreateRequest.setProcessDefinitionKey(demoVacation.getActivitiCode());//设置流程编码
        instanceCreateRequest.setVariables(demoVacation.getVariable());
        activitiService.startProcess(iRequest, instanceCreateRequest);
    }
}