package com.hand.user.mapper;

import java.util.List;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.user.dto.HapDemo;

public interface HapDemoMapper extends Mapper<HapDemo>{

	
	List<HapDemo> selectAllDemo(HapDemo hapDemo);





	
	
	
}