package com.hand.audit.controllers;

import com.hand.audit.dto.HapDemoA;
import com.hand.audit.service.IHapDemoAService;
import com.hand.hap.audit.service.IAuditRecordService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.system.dto.ResponseData;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

    @Controller
    public class HapDemoAController extends BaseController{

        @Autowired
        private IAuditRecordService auditUserService;
        /**
         *  查询供应商的审计记录
         * @param page
         * @param pageSize
         * @return
         */
        @PostMapping("/y/hap/demo/a/queryProAudit")
        @ResponseBody
        public ResponseData queryAuditUser(HttpServletRequest request,
                                           @ModelAttribute HapDemoA dto,
                                           @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                           @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize){
            IRequest iRequest = createRequestContext(request);
            return new ResponseData(auditUserService.selectAuditRecord(iRequest, dto, page, pageSize));
        }

        /**
         *  审计供应商的详细审计记录
         * @return
         */
        @PostMapping("/y/hap/demo/a/queryProAuditDetail")
        @ResponseBody
        public ResponseData queryAuditUserDetail(HttpServletRequest request,
                                                 @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                                 @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
                                                 @ModelAttribute HapDemoA dto){
            IRequest iRequest = createRequestContext(request);
            //封装为DTO 可条件查询
            return new ResponseData(auditUserService.selectAuditRecordDetail(iRequest, dto, page, pageSize));
        }

    }