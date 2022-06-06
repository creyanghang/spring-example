package com.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yh
 * @description
 * @date 2022/6/6
 */
@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {
    // 线程安全 AtomicInteger
    private AtomicInteger count = new AtomicInteger(0);
    @GetMapping("/get")
    private String hello() {
        // 每次进来如打印下日志
        log.info("{} 啪...我第{}次进来了.", LocalDateTime.now(),  count.addAndGet(1));
        // 每次进来new 个大对象，便于监控观察堆内存变化
        byte[] bytes = new byte[100*1024*1024];
        log.info("new了 100MB");
        return "hi springboot addmin " + LocalDateTime.now();
    }
}
