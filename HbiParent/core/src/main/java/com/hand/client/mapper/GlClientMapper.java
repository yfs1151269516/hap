package com.hand.client.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.client.dto.GlClient;

import java.util.List;

public interface GlClientMapper extends Mapper<GlClient>{

    void GLClientbytpye(GlClient glClient);

    List<GlClient> selectAllGLClient(GlClient record);

    void GLClientbyID (GlClient record);
}