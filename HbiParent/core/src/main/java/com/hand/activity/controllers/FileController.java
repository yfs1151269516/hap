package com.hand.activity.controllers;

import com.hand.hap.attachment.FileInfo;
import com.hand.hap.attachment.Uploader;
import com.hand.hap.attachment.UploaderFactory;
import com.hand.hap.attachment.controllers.AttachmentController;
import com.hand.hap.attachment.dto.AttachCategory;
import com.hand.hap.attachment.dto.Attachment;
import com.hand.hap.attachment.dto.SysFile;
import com.hand.hap.attachment.exception.AttachmentException;
import com.hand.hap.attachment.exception.UniqueFileMutiException;
import com.hand.hap.attachment.service.IAttachCategoryService;
import com.hand.hap.attachment.service.ISysFileService;
import com.hand.hap.core.util.FormatUtil;
import com.hand.hap.core.util.UploadUtil;
import com.hand.hap.security.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.activity.dto.File;
import com.hand.activity.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Controller
    public class FileController extends BaseController{

    @Autowired
    private IFileService service;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private IAttachCategoryService categoryService;
    @Autowired
    private ISysFileService fileService;

    private static final String MESSAGE_NAME = "message";
    private static final String MESG_UNIQUE = "Unique";
    private static final String MESG_SUCCESS = "success";
    private static final String INFO_NAME = "info";
    private static final String TYPEORKEY_EMPTY = "TYPEORKEY_EMPTY";
    private static final String TYPE_ERROR = "SOURCETYPE_ERROR";
    private static final String DATABASE_ERROR = "DATABASE_ERROR";
    private static final String IMAGE_MIME_PREFIX = "image";
    private static final String FILE_NAME = "file";
    private static final Integer BUFFER_SIZE = 1024;
    private static final Float COMPRESS_SIZE = 40.0F;
    private static final Float BYTE_TO_KB = 1024.0F;
    private static final String ENC = "UTF-8";
    private static Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @RequestMapping(value = "/yfs/file/query")
    @ResponseBody
    public ResponseData query(File dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.selectAllFile(requestContext,page,pageSize,dto));
    }

    @RequestMapping(value = "/yfs/file/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<File> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/yfs/file/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<File> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    @RequestMapping(
            value = {"/yfs/file/upload"},
            method = {RequestMethod.POST},
            produces = {"text/plain;charset=UTF-8"}
    )
    public ResponseData upload(HttpServletRequest request, Locale locale, String contextPath) throws Exception {
        Uploader uploader = UploaderFactory.getMutiUploader();
        uploader.init(request);
        String status = uploader.getStatus();
        if ("NOT_FILE_ERROR".equals(status)) {
            throw new AttachmentException("error.upload.not.file.form", "error.upload.not.file.form", new Object[0]);
        } else if ("LIMIT_SIZE_MAX_ERROR".equals(status)) {
            throw new AttachmentException("error.upload.total.size.limit.exceeded", "error.upload.total.size.limit.exceeded", new Object[0]);
        } else if ("LIMIT_UPLOAD_NUM_ERROR".equals(status)) {
            throw new AttachmentException("error.upload.total.num.limit.exceeded", "error.upload.total.num.limit.exceeded", new Object[0]);
        } else if ("UPLOAD_ERROR".equals(status)) {
            throw new AttachmentException("error.upload.unknown", "error.upload.unknown", new Object[0]);
        } else {
            String associationTableId=uploader.getParams("associationTableId");
            String sourceType = uploader.getParams("sourceType");
            String sourceKey = uploader.getParams("sourceKey");
            ResponseData response = new ResponseData();
            response.setMessage(this.messageSource.getMessage("hap.upload_success", (Object[])null, locale));
            if (StringUtils.isBlank(sourceType)) {
                throw new AttachmentException("msg.warning.upload.sourcetype.empty", "msg.warning.upload.sourcetype.empty", new Object[0]);
            } else {
                IRequest requestContext = this.createRequestContext(request);
                AttachCategory category = this.categoryService.selectAttachByCode(requestContext, sourceType);
                if (category == null) {
                    throw new AttachmentException("msg.warning.upload.folder.notfound", "msg.warning.upload.folder.notfound", new String[]{sourceType});
                } else {
                    UploadUtil.initUploaderParams(uploader, category);
                    List<FileInfo> fileInfoList = uploader.upload();
                    status = uploader.getStatus();
                    if ("FILE_PROCESS_ERROR".equals(status)) {
                        throw new AttachmentException("error.upload.file.process", "error.upload.file.process", new Object[0]);
                    } else {
                        Attachment attach = UploadUtil.genAttachment(category, sourceKey, requestContext.getUserId(), requestContext.getUserId());
                        List<SysFile> sysFileList = new ArrayList();
                        Iterator var14 = fileInfoList.iterator();

                        while(var14.hasNext()) {
                            FileInfo fileInfo = (FileInfo)var14.next();

                            try {
                                SysFile sysFile = UploadUtil.genSysFile(fileInfo, requestContext.getUserId(), requestContext.getUserId());
                                if ("Y".equals(category.getIsUnique())) {
                                    sysFile = this.fileService.updateOrInsertFile(requestContext, attach, sysFile);
                                } else {
                                    this.fileService.insertFileAndAttach(requestContext, attach, sysFile);
                                }
                                sysFile.setFilePath((String)null);
                                sysFile.setFileSizeDesc(FormatUtil.formatFileSize(sysFile.getFileSize()));
                                TokenUtils.generateAndSetToken(TokenUtils.getSecurityKey(request.getSession(false)), sysFile);
                                sysFileList.add(sysFile);
                            } catch (UniqueFileMutiException var18) {
                                response.setSuccess(false);
                                response.setMessage(this.messageSource.getMessage("hap.mesg_unique", (Object[])null, locale));
                                break;
                            } catch (Exception var19) {
                                if (logger.isErrorEnabled()) {
                                    logger.error("database error", var19);
                                }

                                java.io.File file = fileInfo.getFile();
                                if (file.exists()) {
                                    file.delete();
                                }

                                response.setSuccess(false);
                                break;
                            }
                        }

                        response.setRows(sysFileList);
                        return response;
                    }
                }
            }
        }
    }



    }