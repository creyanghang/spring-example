package com.netty.controller;

import com.netty.config.NettyConfig;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author yh
 * @description
 * @date 2022/6/3
 */
@Slf4j
@RestController
@RequestMapping("/websocket")
public class PushMsgController {

    @RequestMapping("/pushMsgToOne")
    public void pushMsgToOne(String userId, String msg) {
        Channel channel = NettyConfig.getChannel(userId);
        if (Objects.isNull(channel)) {
            log.info("{}--未连接socket服务器", userId);
            return;
        }
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

    @RequestMapping("/pushMsgToAll")
    public void pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
