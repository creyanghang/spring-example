package com.scheduler.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yh
 * @description
 * @date 2022/6/4
 */
@Component
public class TaskService {

    /**
     * 异步定时任务
     * @author  yh
     * @date  2022/6/4
     */
    @Scheduled(cron = "${spring.task-cron}")
    @Async
    public void ScanUserState() throws InterruptedException {
        System.out.println("执行了");
        Thread.sleep(5000);
    }
}
