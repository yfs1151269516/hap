package com.hand.audit.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.audit.dto.HapDemoA;
import com.hand.audit.mapper.HapDemoAMapper;
import com.hand.hap.audit.service.IAuditRecordService;
import com.hand.hap.audit.util.AuditRecordUtils;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.BaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Transactional(rollbackFor = Exception.class)
@Service("HapDemoAServiceImpl")
public class HapDemoAServiceImpl implements IAuditRecordService {
    @Autowired
    private HapDemoAMapper hapDemoAMapper;



    /**
     * 供应商审计列表
     */
    @Override
    public List<Map<String, Object>> selectAuditRecord(IRequest iRequest, BaseDTO dto, int page, int pageSize) {
        // 分页
        PageHelper.startPage(page, pageSize);
        return AuditRecordUtils.operateAuditRecord(hapDemoAMapper.selectAuditProduct((HapDemoA)dto));

    }
    /**
     * 供应商审计明细
     */
    @Override
    public List selectAuditRecordDetail(IRequest iRequest, BaseDTO dto, int page, int pageSize) {
        // 分页
        PageHelper.startPage(page,pageSize);
        List<Map<String,Object>> selectAuditProductDetail = hapDemoAMapper.selectAuditProductDetail((HapDemoA) dto);
        return AuditRecordUtils.operateAuditRecordSingleDetail(selectAuditProductDetail);

    }


}