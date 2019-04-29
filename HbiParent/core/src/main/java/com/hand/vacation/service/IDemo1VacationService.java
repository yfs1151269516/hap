package com.hand.vacation.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.vacation.dto.Demo1Vacation;

public interface IDemo1VacationService {
    /**工作流启动.
     *
     * @param iRequest
     * @param
     */
    public void startBill(IRequest iRequest, Demo1Vacation demoVacation);
}