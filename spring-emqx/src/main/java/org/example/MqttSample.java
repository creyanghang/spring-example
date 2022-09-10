package org.example;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * CMQ X CLOUD 发布订阅
 *
 * @author: yh
 * @date: 2022/9/10
 */
@RequestMapping(value = "/MqttClient")
@RestController
public class MqttSample {
    MqttClient client = null;
    // 发布、订阅主题
    String topic = "test/topic";
    // 消息内容
    String content = "Hello World EMQX";
    // qos消息的服务质量可选值：0 1 2
    int qos = 2;
    // EMQ 部署控制台的连接地址
    String broker = "tcp://q123321.cn-shenzhen.emqx.cloud:11578";
    String clientId = MqttClient.generateClientId();


    public MqttSample() {
        //  持久化
        MemoryPersistence persistence = new MemoryPersistence();
        // MQTT 连接选项
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // 设置认证信息，配置的账号 密码
        connOpts.setUserName("exqcloud");
        connOpts.setPassword("hello".toCharArray());
        try {
            client = new MqttClient(broker, clientId, persistence);
            // 设置回调
            client.setCallback(new SampleCallback());
            // 建立连接
            System.out.println("Connecting to broker: " + broker);
            client.connect(connOpts);
            System.out.println("Connected to broker: " + broker);
            // 订阅 topic
            client.subscribe(topic, qos);
            System.out.println("Subscribed to topic: " + topic);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 消息发布
     *
     * @author: yh
     * @date: 2022/9/10
     */
    @RequestMapping(value = "/send")
    public void send() {
        try {
            // 发布消息
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            //向服务器上的topic发布消息
            client.publish(topic, message);
            System.out.println("Message published");
            // 断开连接
//            client.disconnect();
//            System.out.println("Disconnected");
            // 关闭客户端
//            client.close();
//            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
