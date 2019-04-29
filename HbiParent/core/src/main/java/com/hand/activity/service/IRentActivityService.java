package com.hand.activity.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.service.IBaseService;
import com.hand.activity.dto.RentActivity;

import java.io.InputStream;
import java.util.List;

public interface IRentActivityService extends IBaseService<RentActivity>, ProxySelf<IRentActivityService>{

    List<RentActivity> selectRentActivity(IRequest requestContext, int page, int pageSize, RentActivity rentActivity);

    List<RentActivity> selectRentActivityAA(IRequest requestContext, int page, int pageSize, RentActivity rentActivity);

    void submitAndAct(IRequest requestContext, RentActivity dto);

    void updateActiveStatus(RentActivity rentActivity);

    void updateByTimeActiveStatus();

    void updateByTimeActiveStatusaa();

    ResponseData importExcel(IRequest requestContext,InputStream is, String fileName) throws Exception;
}