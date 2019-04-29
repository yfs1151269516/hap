package com.hand.client.service;

import com.hand.activity.dto.RentActivity;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import com.hand.client.dto.GlClient;

import java.util.List;

public interface IGlClientService extends IBaseService<GlClient>, ProxySelf<IGlClientService>{

    void GLClientbytpye(GlClient glClient);

    List<GlClient> selectAllGLClient(GlClient record);

    List<GlClient> selectAllGLClient(IRequest requestContext, int page, int pageSize, GlClient record);

    void GLClientbyID (List<GlClient> record);
}