package com.hand.activity.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.activity.dto.File;

import java.util.List;

public interface IFileService extends IBaseService<File>, ProxySelf<IFileService>{
    List<File> selectAllFile(IRequest requestContext, int page, int pageSize, File file);

}