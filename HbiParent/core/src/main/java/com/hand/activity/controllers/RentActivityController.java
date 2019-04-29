package com.hand.activity.controllers;

import com.map.ActivityWebService;
import com.map.ActivityWebService_Service;
import com.map.CallProductVo;
import com.map.RequestVo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.activity.dto.RentActivity;
import com.hand.activity.service.IRentActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Controller
    public class RentActivityController extends BaseController{

        @InitBinder
        protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.setLenient(false);
            CustomDateEditor dateEditor = new CustomDateEditor(simpleDateFormat, true);
            binder.registerCustomEditor(Date.class, dateEditor);
        }



        @Autowired
    private IRentActivityService service;


    @RequestMapping(value = "/yfs/rent/activity/query")
    @ResponseBody
    public ResponseData query(RentActivity dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectRentActivity(requestContext,page,pageSize,dto));
    }
        @RequestMapping(value = "/yfs/rent/activity/queryA")
        @ResponseBody
        public ResponseData queryA(RentActivity dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
            IRequest requestContext = createRequestContext(request);
            return new ResponseData(service.selectRentActivityAA(requestContext,page,pageSize,dto));
        }

    @RequestMapping(value = "/yfs/rent/activity/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<RentActivity> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/yfs/rent/activity/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<RentActivity> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }


        @RequestMapping(value = "/yfs/rent/activity/submitAndAct")
        @ResponseBody
          public ResponseData submitAndAct(HttpServletRequest request,@RequestBody List<RentActivity> dto){
            IRequest requestCtx = createRequestContext(request);
            service.submitAndAct(requestCtx,dto.get(0));

            return new ResponseData();
          }
        /***自定义导入*****/
        @RequestMapping(
                value = {"/wht/ora/20796/org/access/export/excel/import/custom"},
                method = {RequestMethod.POST},
                produces = "application/json; charset=utf-8"
        )
        public ResponseData uploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
            ResponseData rd = new ResponseData();
            IRequest requestCtx = createRequestContext(request);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
                    request;
            MultipartFile file = multipartRequest.getFile("upfile");
            if (file == null || file.isEmpty()) {
                rd.setSuccess(false);
                rd.setMessage("文件为空");
                return rd;
            }
            InputStream in = file.getInputStream();
            //importExcel()方法在下面的IOra20796OrgAccessService定义
            return service.importExcel(requestCtx,in, file.getOriginalFilename());
        }
    @RequestMapping(value = "/yfs/rent/activity/WebService")
    @ResponseBody
    public ResponseData WebService(RequestVo vo){
        ActivityWebService_Service factory = new ActivityWebService_Service();
        ActivityWebService Port = factory.getActivityWebServiceImplPort();
        CallProductVo callBack = Port.getCallBack(vo);
        List<CallProductVo> callBacks= new ArrayList<>();
        callBacks.add(callBack);
        return  new ResponseData(callBacks);
    }

    }