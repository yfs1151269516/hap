package com.hand.yfsrole.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.hand.yfsrole.dto.YUserRole;
import com.hand.yfsrole.service.IYUserRoleService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class YUserRoleServiceImpl extends BaseServiceImpl<YUserRole> implements IYUserRoleService{

}