package com.thread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yh
 * @description
 * @date 2022/6/20
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ServletWebServerApplicationContext applicationContext;

    @GetMapping("/get")
    public String test() throws InterruptedException {
//        for (int i = 0; i < 500; i++) {
//            new Thread(()->{
//                try {
//                    Thread.sleep(20);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(Thread.currentThread().getName());
//            }).start();
//        }
        String tomcatLog = ((TomcatWebServer) applicationContext.getWebServer()).getTomcat().getConnector().getProtocolHandler().getExecutor().toString();
        System.out.println(tomcatLog);
        String name = Thread.currentThread().getName();
        System.out.println(name);
        Thread.sleep(20);
        return tomcatLog;
    }
}
