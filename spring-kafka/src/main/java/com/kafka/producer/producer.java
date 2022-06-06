package com.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 生产者
 * @auth yh
 * @date 2022/5/10
 */
@RestController
@RequestMapping("/kafka")
public class producer {
    private final static String topic = "first";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 简单发送消息
     *
     * @param msg 消息内容
     * @author yh
     * @date 2022/6/3
     */
    @RequestMapping("/send")
    public void send(String msg) {
        // 指定topic、分区、key发送
//         kafkaTemplate.send(topic, 1, "2" , msg);
        kafkaTemplate.send(topic, msg).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败：" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });
    }

    /**
     * 生产者事务发送：需配置transaction-id-prefix开启事务
     *
     * @param msg 消息内容
     * @author yh
     * @date 2022/5/11
     */
    @Transactional
    @RequestMapping("/transaction")
    public void transaction(String msg) {
        kafkaTemplate.send(topic, msg);

        // 内部异常后两次发送都会失败
        int a = 1 / 0;
        kafkaTemplate.send(topic, "_____" + msg);
    }

    /**
     * 第二种事务发送
     *
     * @param msg 消息内容
     * @author yh
     * @date 2022/5/11
     */
    @RequestMapping("/transaction2")
    public void transaction2(String msg) {
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback() {
            @Override
            public Object doInOperations(KafkaOperations kafkaOperations) {
                kafkaOperations.send(topic, msg);
                int a = 1 / 0;
                return true;
            }
        });
    }
}