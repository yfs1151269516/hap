package com.hand.audit.mapper;

import com.hand.audit.dto.HapDemoA;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.BaseDTO;
import net.sf.ehcache.util.ProductInfo;

import java.util.List;
import java.util.Map;

public interface HapDemoAMapper extends Mapper<HapDemoA>{

    /**
     * 审计列表
     * @param dto
     * @return
     */
    List<Map<String, Object>> selectAuditProduct(HapDemoA dto);

    /**
     * 审计明细
     * @param dto
     * @return
     */
    List<Map<String, Object>> selectAuditProductDetail(HapDemoA dto);

}