package com.hand.client.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.client.mapper.GlClientMapper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.client.dto.GlClient;
import com.hand.client.service.IGlClientService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GlClientServiceImpl extends BaseServiceImpl<GlClient> implements IGlClientService{
    @Autowired
    GlClientMapper glClientMapper;

    @Override
    public void GLClientbytpye(GlClient glClient){
    glClientMapper.GLClientbytpye(glClient);
    }

    @Override
   public List<GlClient> selectAllGLClient(GlClient record){
      return   glClientMapper.selectAllGLClient(record);
    }
    @Override
    public List<GlClient> selectAllGLClient(IRequest requestContext, int page, int              pageSize, GlClient record){
        PageHelper.startPage(page, pageSize);
        return glClientMapper.selectAllGLClient(record);
    }
    @Override
    public void GLClientbyID (List<GlClient> record){
        for(GlClient glClient:record){
            glClientMapper.GLClientbyID(glClient);
        }

    }
}