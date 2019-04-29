package com.hand.user.service.impl;

import com.demo.ProductCache;
import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;

import java.util.ArrayList;
import java.util.List;


import com.hand.vacation.dto.Demo1Vacation;
import com.hand.vacation.service.IDemo1VacationService;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.user.dto.HapDemo;
import com.hand.user.mapper.HapDemoMapper;
import com.hand.user.service.IHapDemoService;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class HapDemoServiceImpl extends BaseServiceImpl<HapDemo> implements IHapDemoService{

	
	@Autowired
	private HapDemoMapper hapDemoMapper;

	@Autowired
	private ProductCache productCache;

	@Autowired
	private IDemo1VacationService Service;

	@Override
	public List<HapDemo> selectAllDemo(IRequest requestContext,HapDemo dto, int page,int pageSize){
	PageHelper.startPage(page, pageSize);
		List<HapDemo> value = productCache.getValue("demo");




		//========================================
		if(null==dto.getDemoId()){
			Demo1Vacation dto1 =new Demo1Vacation();
			createParams(dto1);
			Service.startBill(requestContext,dto1);
		}

		//========================================


		return hapDemoMapper.selectAllDemo(dto);
	}


	private void createParams(Demo1Vacation dto){

		dto.setActivitiCode("test");
		dto.setBusinessKey("5");//
		List<RestVariable> variables = new ArrayList<>();
		RestVariable parames1 = new RestVariable();
		parames1.setName("parames1");
		parames1.setType("string");
		parames1.setValue("parames1");



		RestVariable primarykey = new RestVariable();
		primarykey.setName("primarykey");
		primarykey.setType("long");
		primarykey.setValue(67);


		RestVariable actCode = new RestVariable();
		actCode.setName("actCode");
		actCode.setType("string");
		actCode.setValue("test");





		RestVariable amount = new RestVariable();
		amount.setName("amount");
		amount.setType("long");
		amount.setValue(19999);

		variables.add(parames1);
		variables.add(primarykey);
		variables.add(actCode);
		variables.add(amount);
		dto.setVariable(variables);

	}
	
	
}