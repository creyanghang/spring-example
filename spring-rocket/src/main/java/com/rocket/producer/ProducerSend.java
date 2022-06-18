package com.rocket.producer;

import com.alibaba.fastjson.JSONObject;
import com.rocket.listener.SendCallbackListener;
import com.rocket.util.ListSplitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yh
 * @description 生产者
 * @date 2022/6/18
 */
@RequestMapping(value = "ProducerSend")
@RestController
@Slf4j
public class ProducerSend {
    @Autowired
    private RocketMQTemplate template;
    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.sync-tag}")
    private String syncTag;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.async-tag}")
    private String asyncag;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.oneway-tag}")
    private String onewayTag;

    /**
     * 普通发送
     *
     * @author yh
     * @date 2022/6/18
     */
    @GetMapping(value = "send")
    public void send() {
        DefaultMQProducer producer = template.getProducer();
        int num = 10;
        while (num > 0) {
            String message = "sendMessage" + num;
            Message message1 = new Message("test-topic-Sync", "test-tag-Sync", message.getBytes(StandardCharsets.UTF_8));

            try {
                SendResult sendResult = producer.send(message1);
                if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                    System.out.println("普通消息发送成功");
                } else {
                    System.out.println("普通消息发送失败:" + num + "。" + sendResult.getSendStatus());

                }
            } catch (Exception e) {
                System.out.println("普通消息发送报错" + e);
            }
            num--;
        }
    }

    /**
     * 事务消息
     *
     * @param id 消息
     */
    @RequestMapping("/pushTransactionMessage")
    public void pushTransactionMessage(@RequestParam("id") int id) {
        // 创建消息
        String messageStr = "order id : " + id;
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .setHeader("money", 10)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, id)
                .build();
        TransactionSendResult transactionSendResult = template.sendMessageInTransaction(syncTag, message, null);
        log.info("pushTransactionMessage result : " + JSONObject.toJSONString(transactionSendResult));
        log.info("pushTransactionMessage finish : " + id);
    }

    /**
     * 发送异步消息
     *
     * @param id 消息
     */
    @RequestMapping("/pushAsyncMessage")
    public void pushAsyncMessage(@RequestParam("id") int id) {
        log.info("pushAsyncMessage start : " + id);
        // 构建消息
        String messageStr = "order id : " + id;
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置发送地和消息信息并发送异步消息
        template.asyncSend(asyncag, message, new SendCallbackListener(id));
        log.info("pushAsyncMessage finish : " + id);
    }

    /**
     * 发送单向消息（不关注发送结果：记录日志）
     *
     * @param id 消息
     */
    @RequestMapping("/pushOneWayMessage.action")
    public void pushOneWayMessage(@RequestParam("id") int id) {
        log.info("pushOneWayMessage start : " + id);
        // 构建消息
        String messageStr = "order id : " + id;
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置发送地和消息信息并发送单向消息
        template.sendOneWay(onewayTag, message);
        log.info("pushOneWayMessage finish : " + id);
    }

    /**
     * rocketmq 延迟消息
     *
     * @param id 消息
     * @return 结果
     */
    @RequestMapping("/pushDelayMessage.action")
    public void pushDelayMessage(@RequestParam("id") int id) {
        log.info("pushDelayMessage start : " + id);
        // 构建消息
        String messageStr = "order id : " + id;
        org.springframework.messaging.Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        // 设置超时和延时推送
        // 超时时针对请求broker然后结果返回给product的耗时
        // 现在RocketMq并不支持任意时间的延时，需要设置几个固定的延时等级，从1s到2h分别对应着等级1到18
        // private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
        SendResult sendResult = template.syncSend(syncTag, message, 1 * 1000l, 4);
        log.info("pushDelayMessage finish : " + id + ", sendResult : " + JSONObject.toJSONString(sendResult));

        // 解析发送结果
        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
            log.info("send status: ok");
        }
    }

    /**
     * 同时发送10个单向消息（真正的批量）
     *
     * @param id 消息
     */
    @RequestMapping("/pushBatchMessage.action")
    public void pushBatchMessage(@RequestParam("id") int id) {
        log.info("pushBatchMessage start : " + id);
        // 创建消息集合
        List<org.springframework.messaging.Message> messages = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String myId = id + "" + i;
            // 处理当前订单唯一标识
            String messageStr = "order id : " + myId;
            org.springframework.messaging.Message<String> message = MessageBuilder.withPayload(messageStr)
                    .setHeader(RocketMQHeaders.KEYS, myId)
                    .build();
            messages.add(message);
        }
        // 批量下发消息到broker,不支持消息顺序操作，并且对消息体有大小限制（不超过4M）
        ListSplitter splitter = new ListSplitter(messages, 1024 * 1024 * 4);
        while (splitter.hasNext()) {
            List<Message> listItem = splitter.next();
            template.syncSend(syncTag, listItem);
        }
        log.info("pushBatchMessage finish : " + id);
    }

}
