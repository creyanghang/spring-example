package com.kafka.config;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description 自定义定时监听器，定时监听、停止topic
 * @auth yh
 * @date 2022/5/11
 */
@EnableScheduling
@Component
public class CronTimer {
    /**
     * @KafkaListener 注解所标注的方法并不会在IOC容器中被注册为Bean， 而是会被注册在KafkaListenerEndpointRegistry中，
     * 而KafkaListenerEndpointRegistry在SpringIOC中已经被注册为Bean
     **/
    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @Autowired
    private ConsumerFactory consumerFactory;

    // 监听器容器工厂(设置禁止KafkaListener自启动)
    @Bean
    public ConcurrentKafkaListenerContainerFactory delayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(consumerFactory);
        //禁止KafkaListener自启动
        container.setAutoStartup(false);
        return container;
    }


    /**
     * 定时启动监听器
     *
     * @author yh
     * @date 2022/5/11
     */
    @Scheduled(cron = "*/10 * * * * ?")
    public void startListener() {
        System.out.println("启动监听器..." + DateUtil.date());
        // "timingConsumer"是@KafkaListener注解后面设置的监听器ID,标识这个监听器
        if (!registry.getListenerContainer("timingConsumer").isRunning()) {
            registry.getListenerContainer("timingConsumer").start();
        }
        //registry.getListenerContainer("timingConsumer").resume();
    }

    /**
     * 定时停止监听器
     *
     * @author yh
     * @date 2022/5/11
     */
    @Scheduled(cron = "* 1 * * * ?")
    public void shutDownListener() {
        System.out.println("关闭监听器..." + DateUtil.date());
        registry.getListenerContainer("timingConsumer").pause();
    }

}