package com.hand.activity.webservice.service.impl;

import com.hand.activity.dto.RentActivity;
import com.hand.activity.mapper.RentActivityMapper;
import com.hand.activity.webservice.service.activityWebService;
import com.hand.activity.webservice.vo.CallProductVoo;
import com.hand.activity.webservice.vo.RequestVoo;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.hand.activity.webservice.service.activityWebService", serviceName = "activityWebService")
public class activityWebServiceImpl implements activityWebService {

        @Autowired
        private RentActivityMapper rentActivityMapper;


        @Override
        public CallProductVoo getCallBack(RequestVoo vo) {
            RentActivity rentActivity =new RentActivity();
            rentActivity.setActivityId(vo.getActivity_id());
            List<RentActivity> rentActivities = rentActivityMapper.selectRentActivity(rentActivity);
            CallProductVoo callProductVo=new CallProductVoo();
            if(null == rentActivities || rentActivities.size() ==0 ){
                callProductVo.setTushi("活动不存在或者已经结束");
                return callProductVo;
            }
            callProductVo.setRuleName(rentActivityMapper.selectRuleName(rentActivity));
            callProductVo.setClientName(rentActivityMapper.selectClientName(rentActivity));
            callProductVo.setActivityId(rentActivities.get(0).getActivityId());
            callProductVo.setEventName(rentActivities.get(0).getEventName());
            callProductVo.setCreatedByBy(rentActivities.get(0).getCreatedByBy());
            callProductVo.setCreateTime(rentActivities.get(0).getCreateTime());
            callProductVo.setActivityAmount(rentActivities.get(0).getActivityAmount());
            callProductVo.setReleaseDate(rentActivities.get(0).getReleaseDate());
            callProductVo.setReleaseEndDate(rentActivities.get(0).getReleaseEndDate());
            callProductVo.setActivities(rentActivities.get(0).getActivities());
            callProductVo.setPrivilege(rentActivities.get(0).getPrivilege());
            if("new".equals(rentActivities.get(0).getActiveStatus())){
                callProductVo.setActiveStatus("新建");
            }else if("under_review".equals(rentActivities.get(0).getActiveStatus())){
                callProductVo.setActiveStatus("审核中");
            }else if("examination_passed".equals(rentActivities.get(0).getActiveStatus())){
                callProductVo.setActiveStatus("审核通过");
            }else if("Audit_rejection".equals(rentActivities.get(0).getActiveStatus())){
                callProductVo.setActiveStatus("审核拒绝");
            }else if("announcing".equals(rentActivities.get(0).getActiveStatus())){
                callProductVo.setActiveStatus("发布中");
            }
            return callProductVo;
        }
}
