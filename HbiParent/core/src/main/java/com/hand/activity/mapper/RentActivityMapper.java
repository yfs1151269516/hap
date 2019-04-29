package com.hand.activity.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.activity.dto.RentActivity;
import org.activiti.engine.impl.persistence.ByteArrayRefTypeHandler;

import java.util.List;

public interface RentActivityMapper extends Mapper<RentActivity>{

    List<RentActivity> selectRentActivity(RentActivity rentActivity);

    List<RentActivity> selectRentActivityAA(RentActivity rentActivity);

    void updateActiveStatus(RentActivity rentActivity);

    void updateByTimeActiveStatus();

    void updateByTimeActiveStatusaa();

    List<RentActivity> selectStartActivity();

    List<RentActivity> selectEndActivity();

    List<String>  selectRuleName(RentActivity rentActivity);

    List<String>  selectClientName(RentActivity rentActivity);




}