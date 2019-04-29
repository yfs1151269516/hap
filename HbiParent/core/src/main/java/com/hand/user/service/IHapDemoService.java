package com.hand.user.service;

import java.util.List;

import com.hand.hap.core.IRequest;
import org.springframework.web.bind.annotation.RequestParam;

import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.user.dto.HapDemo;

public interface IHapDemoService extends IBaseService<HapDemo>, ProxySelf<IHapDemoService>{

	List<HapDemo> selectAllDemo(IRequest requestContext,HapDemo dto, int page, int pageSize);
	
}