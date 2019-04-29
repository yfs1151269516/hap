package com.hand.activity.vacation.service;

import com.hand.activity.vacation.dto.ActivityVacation;
import com.hand.hap.core.IRequest;

public interface ActivityVacationService {
    /**工作流启动.
     *
     * @param iRequest
     * @param
     */
    public void startBill(IRequest iRequest,ActivityVacation dto);
}