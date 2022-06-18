package com.rocket.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author yh
 * @description
 * @date 2022/6/18
 */
@Service
@RocketMQMessageListener(topic = "test-topic-Sync", consumerGroup = "my-consumer-group")
public class Consumer1 implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}
