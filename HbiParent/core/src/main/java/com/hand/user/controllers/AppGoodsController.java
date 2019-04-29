package com.hand.user.controllers;

import com.hand.hap.intergration.annotation.HapInbound;
import com.hand.hap.system.controllers.BaseController;
import com.hand.user.dto.HapDemo;
import com.hand.user.service.IHapDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = { "/api/public/app" })
public class AppGoodsController extends BaseController{

    @Autowired
    private IHapDemoService hapDemoService;

    @HapInbound(apiName="有效商品接口")
    @RequestMapping(value = "/goods/appQueryGoodList", produces = "application/json")
    @ResponseBody
    public Map<String,Object> appQueryGoodList(@RequestBody HapDemo dto, HttpServletRequest request) {
        Map<String,Object> map=new HashMap<>();
        map.put("goodsList", "1223");
        map.put("page","45786" );
        return map;
    }



}
