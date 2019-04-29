package com.hand.client.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.hand.client.dto.Client;
import com.hand.client.service.IClientService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientServiceImpl extends BaseServiceImpl<Client> implements IClientService{

}