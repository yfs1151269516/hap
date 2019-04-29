package com.hand.activity.webservice.service;

import com.hand.activity.webservice.vo.CallProductVoo;
import com.hand.activity.webservice.vo.RequestVoo;


import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface activityWebService {

	CallProductVoo getCallBack(@WebParam(name="result")RequestVoo vo);
        }
