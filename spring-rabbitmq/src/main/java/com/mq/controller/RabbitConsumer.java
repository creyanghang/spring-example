package com.mq.controller;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * consumer
 *
 * @author yh
 * @date 2022/6/4
 */

@Component
public class RabbitConsumer {

    /**
     * ackMode: MANUAL 手动提交ack
     *
     * @param message
     * @param channel
     * @return
     * @author yh
     * @date 2022/6/4
     */

    @RabbitListener(queues = {"demo"}, ackMode = "MANUAL")
    public void consume1(Message message, Channel channel) throws IOException {

        System.out.println("接收到消息1：" + message);
        // 手动确认
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
//      channel.basicAck(deliveryTag, true);
        //第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
//		channel.basicReject(deliveryTag, true);
    }
//
//    @RabbitListener(queues = {"demo"})
//    public void consume2(@Payload String body, Channel channel) throws IOException {
//        System.out.println("接收到消息2：" + body);
//    }

}
