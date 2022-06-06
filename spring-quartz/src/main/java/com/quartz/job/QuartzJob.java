package com.quartz.job;

import cn.hutool.core.date.DateUtil;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzJob extends QuartzJobBean {

    @Async
    @Override
    protected void executeInternal(JobExecutionContext context) {
        String userName = (String) context.getJobDetail().getJobDataMap().get("userName");
        System.out.println("userName:" + userName + DateUtil.date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
