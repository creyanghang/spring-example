package org.example;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 回调类
 * @author:  yh
 * @date:  2022/9/10
 */
public class SampleCallback implements MqttCallback {
    /**
     * 连接丢失
     * @author:  yh
     * @date:  2022/9/10
     */
    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("连接断开：" + cause.getMessage());
    }

    /**
     * 收到消息
     * @author:  yh
     * @date:  2022/9/10
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("接收到消息-- topic：" + topic + "，Qos：" + message.getQos() + "， 内容：" + new String(message.getPayload()));
    }

    /**
     * 消息传递成功
     * @author:  yh
     * @date:  2022/9/10
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("消息发送成功！");
    }
}
