package com.common.conf;

import com.common.aspect.ControllerLogAspect;
import com.common.interceptor.ResumerInterceptor;
import com.common.util.RedisCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @description
 * @auth yh
 * @date 2022/5/29
 */
public class CommonConfiguration {

    /**
     * 綫程池配置
     */
    @Bean
    public TaskExecutorConfig getTaskExecutorConfig() {
        return new TaskExecutorConfig();
    }

    /**
     * http 请求
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @ConditionalOnMissingBean
    public RedisCache redisCache() {
        return new RedisCache();
    }

    @Bean
    @ConditionalOnMissingBean
    public Knife4jConfiguration knife4jConfiguration() {
        return new Knife4jConfiguration();
    }

    @Bean("ResumerInterceptor")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "common.setting.intercept", name = "type", havingValue = "resumer")
    public ResumerInterceptor getRResumerInterceptor() {
        return new ResumerInterceptor();
    }

    @Bean("ResumerInterceptorConfig")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "common.setting.intercept", name = "type", havingValue = "resumer")
    public ResumerInterceptorConfig getUserInterceptorConfig() {
        return new ResumerInterceptorConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public ControllerLogAspect getControllerLogAspect() {
        return new ControllerLogAspect();
    }
}