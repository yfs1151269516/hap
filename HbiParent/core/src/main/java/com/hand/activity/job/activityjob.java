package com.hand.activity.job;

import com.hand.activity.redis.redisCache;
import com.hand.activity.service.IRentActivityService;
import com.hand.hap.job.AbstractJob;
import com.hand.hap.script.service.IScriptService;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class activityjob extends AbstractJob {

    private Logger logger = LoggerFactory.getLogger(activityjob.class);
    @Autowired
    private IRentActivityService service;
    @Autowired
    private redisCache redisCache;
    @Override
    public void safeExecute(JobExecutionContext context) throws Exception {
    service.updateByTimeActiveStatus();

    service.updateByTimeActiveStatusaa();
        redisCache.init();
    }
    @Override
    public boolean isRefireImmediatelyWhenException() {
        return false;
    }
}

