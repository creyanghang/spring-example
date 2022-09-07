//package com.thread.conf;
//
//import org.apache.catalina.connector.Connector;
//import org.apache.coyote.http11.Http11NioProtocol;
//import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author yh
// * @description
// * @date 2022/6/20
// */
//@Configuration
//public class TomcatConnectionCustomizer implements TomcatConnectorCustomizer{
//
//    /**
//     * 配置类: {@link org.springframework.boot.autoconfigure.web.ServerProperties}
//     *
//     * 查看线程数命令
//     * <code>pstree -p pid | wc -l</code>
//     */
//    @Override
//    public void customize(Connector connector) {
//        connector.setPort(8105);
//        // 连接协议处理器. 默认： org.apache.coyote.http11.Http11NioProtocol
//        connector.setAttribute("protocol", "org.apache.coyote.http11.Http11Nio2Protocol");
//
//        /* 参数设置-方式1 */
////        connector.setAttribute("minSpareThreads", 100);
////        connector.setAttribute("maxThreads", 800);
//
//        /* 参数设置-方式2 */
//        Http11NioProtocol protocolHandler = (Http11NioProtocol) connector.getProtocolHandler();
//        // 最小工作线程数, 默认： 10(适当增大一些, 以便应对突然增长的访问量)
//        protocolHandler.setMinSpareThreads(100);
//        // 最大线程数, 默认: 200(4核8g内存, 线程数经验值800, 操作系统做线程之间的切换调度是有系统开销的, 所以不是越多越好)
//        protocolHandler.setMaxThreads(800);
//        // 等待队列长度, 默认100
//        protocolHandler.setAcceptCount(1000);
//        // 最大连接数, 默认： 10000
//        protocolHandler.setMaxConnections(10000);
//        // 连接超时时间, 默认: 60000
//        protocolHandler.setConnectionTimeout(100);
//        // 默认： 1
//        protocolHandler.setAcceptorThreadCount(10);
//        // 30秒内没有请求则服务端自动断开keepalive链接
//        protocolHandler.setKeepAliveTimeout(300000);
//        // 当客户端发送超过10000个请求则自动断开keepalive链接
//        protocolHandler.setMaxKeepAliveRequests(10000);
//    }
//}
