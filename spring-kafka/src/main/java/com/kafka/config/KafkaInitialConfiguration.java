package com.kafka.config;

/**
 * @description 创建topic,启动后会自动创建
 * @auth yh
 * @date 2022/5/11
 */
//@Configuration
//public class KafkaInitialConfiguration {
//    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
//    @Bean
//    public NewTopic initialTopic() {
//        return new NewTopic("testtopic",8, (short) 2 );
//    }
//
//    // 如果要修改分区数，只需修改配置值重启项目即可
//    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
//    @Bean
//    public NewTopic updateTopic() {
//        return new NewTopic("testtopic",10, (short) 2 );
//    }
//}