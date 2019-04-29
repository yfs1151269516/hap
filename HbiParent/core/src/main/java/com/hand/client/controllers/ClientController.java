package com.hand.client.controllers;

import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.client.dto.Client;
import com.hand.client.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
    public class ClientController extends BaseController{

    @Autowired
    private IClientService service;


    @RequestMapping(value = "/yfs/client/query")
    @ResponseBody
    public ResponseData query(Client dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        List<Client> select = service.select(requestContext, dto, page, pageSize);

        return new ResponseData(select);

    }

    @RequestMapping(value = "/yfs/client/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<Client> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/yfs/client/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Client> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }