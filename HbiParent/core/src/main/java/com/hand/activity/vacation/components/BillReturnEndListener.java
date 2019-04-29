package com.hand.activity.vacation.components;

import com.hand.activity.dto.RentActivity;
import com.hand.activity.service.IRentActivityService;
import com.hand.user.dto.HapDemo;
import com.hand.user.service.IHapDemoService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 审批流结束后，触发的事件，已有的方法，绝对不能改名
 * @author jun
 *
 */
@Component
public class BillReturnEndListener implements ExecutionListener, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateExecution arg0) {
        // 业务编码
        String businessKey = arg0.getProcessInstanceBusinessKey();
        // 185054
        //String processInstanceId = arg0.getProcessInstanceId();  当前流程id
        Map<String, Object> variablesMap = arg0.getVariables();
        // TODO 审批流结束后的逻辑处理

        String status = (String) variablesMap.get("approveResult");
        String activitiType = (String) variablesMap.get("actCode");

        log.debug("========监听到=====工作流类型是==" + activitiType + "==操作业务id是==" + businessKey + "==执行的是==" + status);
        if (null == status || "".equals(status)) {
            log.debug("=======非正常状态调用======工作流类型是==" + activitiType + "======此时status是==" + status);
            return;
        } else {
            IRentActivityService service= BillReturnEndListener.getContext().getBean(IRentActivityService.class);
            log.debug("=============此时有监听到工作流, ==========status非null,也不是正常值;而是========" + status);

        if("APPROVED".equals(status)){
            RentActivity rentActivity= new RentActivity();
            rentActivity.setActivityId(Long.parseLong(businessKey));
            rentActivity.setActiveStatus("examination_passed");
            service.updateActiveStatus(rentActivity);
        }
        if("REJECTED".equals(status)){
            RentActivity rentActivity= new RentActivity();
            rentActivity.setActivityId(Long.parseLong(businessKey));
            rentActivity.setActiveStatus("Audit_rejection");
            service.updateActiveStatus(rentActivity);
        }
            }
    }




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BillReturnEndListener.applicationContext = applicationContext; // NOSONAR

    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }

}
