package com.hand.user.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.hand.user.dto.HapEmployee;
import com.hand.user.service.IHapEmployeeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HapEmployeeServiceImpl extends BaseServiceImpl<HapEmployee> implements IHapEmployeeService{

}