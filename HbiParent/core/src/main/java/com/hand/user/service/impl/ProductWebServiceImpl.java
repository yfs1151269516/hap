package com.hand.user.service.impl;

import com.hand.user.Vo.CallProductVo;
import com.hand.user.dto.HapDemo;
import com.hand.user.mapper.HapDemoMapper;
import com.hand.user.service.YYYYYWebService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.hand.user.service.YYYYYWebService", serviceName = "productWebService")
public class ProductWebServiceImpl implements YYYYYWebService{

        @Autowired
        private HapDemoMapper hapDemoMapper;


        @Override
        public CallProductVo getCallBack() {

            HapDemo arg0=new HapDemo();

            List<HapDemo> list=hapDemoMapper.selectAllDemo(arg0);
            CallProductVo callProductVo=new CallProductVo();
            callProductVo.setCode("0909");
            callProductVo.setMessage("成功");
            callProductVo.setName(list.get(0).getName());
            callProductVo.setResult("success");
            return callProductVo;
        }
}
