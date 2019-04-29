package com.hand.user.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.hand.user.dto.HapDemoAssign;
import com.hand.user.service.IHapDemoAssignService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HapDemoAssignServiceImpl extends BaseServiceImpl<HapDemoAssign> implements IHapDemoAssignService{

}