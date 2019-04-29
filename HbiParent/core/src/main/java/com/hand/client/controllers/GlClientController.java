package com.hand.client.controllers;

import com.hand.activity.dto.RentActivity;
import com.hand.activity.service.IRentActivityService;
import com.hand.client.dto.Client;
import com.hand.client.service.IClientService;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.client.dto.GlClient;
import com.hand.client.service.IGlClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

    @Controller
    public class GlClientController extends BaseController{

    @Autowired
    private IGlClientService service;

    @Autowired
    private IClientService iClientService;
    @Autowired
     private    IRentActivityService iRentActivityService;

    @RequestMapping(value = "/yfs/gl/client/query")
    @ResponseBody
    public ResponseData query(GlClient dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        Long[] allRoleId = requestContext.getAllRoleId();
        List<GlClient> glClients = service.selectAllGLClient(requestContext, page, pageSize, dto);
        int i = Arrays.binarySearch(allRoleId,(long)10004);
        for(GlClient glClient :glClients){
            if(i>0){
                glClient.setLanderRole("true");
            }else {
                glClient.setLanderRole("false");
            }
        }


        return new ResponseData(glClients);
    }
               @RequestMapping(value = "/yfs/gl/client/GLClientbyID")
        @ResponseBody
        public ResponseData GLClientbyID(@RequestBody List<GlClient> dto, BindingResult result, HttpServletRequest request){
           service.GLClientbyID(dto);
            return new ResponseData();
        }

        @RequestMapping(value = "/yfs/gl/client/GLClientbytpye")
        @ResponseBody
        public ResponseData GLClientbytpye(@RequestBody List<GlClient> dto, BindingResult result, HttpServletRequest request){
            service.GLClientbytpye(dto.get(0));
            return new ResponseData();
        }



    @RequestMapping(value = "/yfs/gl/client/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<GlClient> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/yfs/gl/client/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<GlClient> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }

        @RequestMapping(value = "/api/public/client/bytpye")
        @ResponseBody
        public ResponseData bytpye(HttpServletRequest request, GlClient dto){

            ResponseData responseData = new ResponseData();
            List<RentActivity> rentActivities = iRentActivityService.selectAll();
            for(RentActivity rentActivity1:rentActivities){
               if(rentActivity1.getActivityId().equals(dto.getActivityId())){
                   if("end".equals(rentActivity1.getActiveStatus())){
                       responseData.setMessage("活动已结束");
                       responseData.setSuccess(false);
                       return responseData;
                   }
                   if("new".equals(rentActivity1.getActiveStatus())){
                       responseData.setMessage("新建活动无法申请");
                       responseData.setSuccess(false);
                       return responseData;
                   }
                   if("under_review".equals(rentActivity1.getActiveStatus())){
                       responseData.setMessage("审核中活动无法申请");
                       responseData.setSuccess(false);
                       return responseData;
                   }
                   if("examination_passed".equals(rentActivity1.getActiveStatus())){
                       responseData.setMessage("活动未发布无法申请");
                       responseData.setSuccess(false);
                       return responseData;
                   }
                   if("Audit_rejection".equals(rentActivity1.getActiveStatus())){
                       responseData.setMessage("活动未通过审核无法申请");
                       responseData.setSuccess(false);
                       return responseData;
                   }

                   List<GlClient> glClients = service.selectAllGLClient(dto);
                   for(GlClient glClient:glClients){
                       if(glClient.getClientId().equals(dto.getClientId())){
                           if(glClient.getActivityId().equals(dto.getActivityId())){
                               if("Audit_rejection".equals(
                                       glClient.getApplicationStatus())){
                                   dto.setApplicationStatus("under_review");
                                   dto.setGlClientId(glClient.getGlClientId());
                                   dto.setApplicationDate(glClient.getApplicationDate());
                                   List<GlClient> dtos =new ArrayList<GlClient>();
                                   dtos.add(dto);
                                   service.GLClientbyID(dtos);
                                   responseData.setMessage("活动重新申请成功");
                                   responseData.setSuccess(true);
                                   return responseData;
                               }
                               responseData.setMessage("已经参与活动");
                               responseData.setSuccess(false);
                               return responseData;
                           }

                       }
                   }
                   dto.setApplicationDate(new Date());
                   dto.setApplicationStatus("under_review");
                   service.GLClientbytpye(dto);
                   responseData.setMessage("活动申请成功");
                   responseData.setSuccess(true);
                   return responseData;
               }
            }
            responseData.setMessage("活动不存在");
            responseData.setSuccess(false);
            return responseData;
        }
    }