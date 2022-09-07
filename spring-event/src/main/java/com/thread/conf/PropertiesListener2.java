package com.thread.conf;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Properties;

/**
 * @author yh
 * @description
 * @date 2022/6/21
 */
@Configuration
public class PropertiesListener2 implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    /**
     * 在生命周期侦听器中添加其他属性源，不用通过配置文件配置
     * @param event event
     * @author: yh
     * @date: 2022/9/1
     */
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Properties props = new Properties();
        //工作线程的最小数量
        props.put("server.tomcat.threads.min-spare", 50);

        //Tomcat线程池最大工作线程数
        props.put("server.tomcat.threads.max", 200);

        //超过maxThread数量，最大等待数
        props.put("server.tomcat.accept-count", 100);

        props.put("server.port", 8088);

        //服务器在任何给定时间接受和处理的最大连接数。一旦达到限制，操作系统仍然可以根据“acceptCount”属性接受连接，默认：8192
        //一瞬间最多能够处理的并发连接数，一个线程可以处理多个连接
        props.put("server.tomcat.max-connections", 10000);

        environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps", props));
    }
//    @Override
//    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
//        ConfigurableEnvironment environment = event.getEnvironment();
//        Properties props = new Properties();
//        props.put("server.tomcat.threads.min-spare", 100);
//        props.put("server.port", 8105);
//        environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps", props));
//        PropertySource<?> myProps = PropertiesPropertySource.named("myProps");
//    }
}
