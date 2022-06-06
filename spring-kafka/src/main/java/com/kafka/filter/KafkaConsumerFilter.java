package com.kafka.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.stereotype.Component;

/**
 * @description 消息过滤器
 * @auth yh
 * @date 2022/5/11
 */
@Component
public class KafkaConsumerFilter {

    @Autowired
    private ConsumerFactory consumerFactory;

    /**
     * 手动提交的监听器工厂 (使用的消费组工厂必须 kafka.consumer.enable-auto-commit = false)
     *
     * @author yh
     * @date 2022/6/3
     */
    @Bean("filterContainerFactory2")
    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        //设置提交偏移量的方式 当Acknowledgment.acknowledge()侦听器调用该方法时，立即提交偏移量
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        factory.setBatchListener(true);
        // 消息过滤策略
        factory.setRecordFilterStrategy(consumerRecord -> {
            if (consumerRecord.value().toString().hashCode() % 2 == 0) {
                return false;
            }
            //返回true消息则被过滤
            return true;
        });
        return factory;
    }

    /**
     * 监听器工厂 批量消费, 也可通过配置文件配置
     *
     * @return
     * @author yh
     * @date 2022/6/3
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true);
        return factory;
    }
}