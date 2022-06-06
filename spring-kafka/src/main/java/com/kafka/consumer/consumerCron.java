package com.kafka.consumer;

import cn.hutool.core.date.DateUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @description 定时启动关闭监听器
 * @auth yh
 * @date 2022/5/11
 */
@Component
public class consumerCron {

    /**
     * 定时监听器消费
     *
     * @param record 接受内容
     * @author yh
     * @date 2022/5/11
     */
    @KafkaListener(id = "timingConsumer", topicPartitions = {
            @TopicPartition(topic = "first", partitionOffsets = {
                    @PartitionOffset(partition = "0", initialOffset = "0")
            }),
    }, containerFactory = "delayContainerFactory")
    public void onMessage1(ConsumerRecord<?, ?> record) {
        System.out.println("消费成功：" + record.topic() + "-" + record.partition() + "-" + record.value() + "__" + DateUtil.date());
    }
}