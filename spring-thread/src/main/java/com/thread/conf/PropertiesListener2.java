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
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        Properties props = new Properties();
        props.put("server.tomcat.threads.min-spare", 100);
        environment.getPropertySources().addFirst(new PropertiesPropertySource("myProps", props));
        PropertySource<?> myProps = PropertiesPropertySource.named("myProps");
    }
}
