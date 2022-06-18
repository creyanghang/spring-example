package com.rocket.listener;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @author yh
 * @description rocketmq异步回调监听
 * @date 2022/6/18
 */
@Slf4j
public class SendCallbackListener implements SendCallback {
    private int id;

    public SendCallbackListener(int id) {
        this.id = id;
    }

    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("CallBackListener on success : " + JSONObject.toJSONString(sendResult));
    }

    @Override
    public void onException(Throwable throwable) {
        log.error("CallBackListener on exception : ", throwable);
    }
}
