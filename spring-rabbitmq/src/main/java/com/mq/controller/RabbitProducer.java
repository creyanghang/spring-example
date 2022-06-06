package com.mq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/msg")
public class RabbitProducer {

    /**
     * exchange类型
     * <p>
     * direct: 默认，直连型交换机
     * fanout: 广播，扇型交换机,没有路由键概念
     * topic: 模式匹配，主题交换机
     * headers
     *
     * @author yh
     * @date 2022/6/4
     */

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * direct类型交换
     *
     * @param msg 消息内容
     * @return result
     * @author yh
     * @date 2022/6/4
     */
    @GetMapping(value = "direct")
    public String sayHello(String msg) {
        rabbitTemplate.convertAndSend("demo", msg);
        return "消息发送成功";
    }

    /**
     * fanout类型交换
     *
     * @param msg 消息内容
     * @return result
     * @author yh
     * @date 2022/6/4
     */
    @GetMapping(value = "broadCast")
    public String broadCast(String msg) {
        // 广播消息到 demoex_fanout 这个 exchange 绑定的所有队列
        // 需要创建fanout类型exchange:demoex_fanout, 新建2个或以上队列绑定到交换机
        rabbitTemplate.convertAndSend("demoex_fanout", "", msg);
        return "广播成功";
    }

    /**
     * topic类型模式
     *
     * @param msg 消息内容
     * @return result
     * @author yh
     * @date 2022/6/4
     */
    @GetMapping(value = "/topic")
    public String pattern(String msg) {
        // 匹配模式demoex_topic
        // 新建三个队列，Routing Key为：demo.#，demo.a.*，demo.a.*.c
        rabbitTemplate.convertAndSend("demoex_topic", "demo.a.b.c", msg);
        return "模式传播成功";
    }
}
