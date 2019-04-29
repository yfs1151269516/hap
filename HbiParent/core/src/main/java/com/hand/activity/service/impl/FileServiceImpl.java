package com.hand.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.activity.dto.RentActivity;
import com.hand.activity.mapper.FileMapper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.activity.dto.File;
import com.hand.activity.service.IFileService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl extends BaseServiceImpl<File> implements IFileService{
    @Autowired
    FileMapper fileMapper;
    @Override
    public List<File> selectAllFile(IRequest requestContext, int page, int pageSize, File file){
        PageHelper.startPage(page, pageSize);
        return fileMapper.selectAllFile(file);
    }
}