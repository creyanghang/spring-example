package com.sample.conf;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
@MapperScan("com.sample.mapper")
public class MybatisPlusConfig {


    private static String modTables;
    private static Map<String, Integer> tableMap = new HashMap<>(16);


    @Value("${spring.datasource.modTables:null}")
    public void setModTables(String modTables) {
        MybatisPlusConfig.modTables = modTables;
    }
    @PostConstruct
    public void init() throws Exception {
        String[] split = modTables.split(",");
        for (String s : split) {
            String[] split1 = s.split("&");
            if (split1.length < 2) {
                throw new RuntimeException("分表配置错误");
            }
            MybatisPlusConfig.tableMap.put(split1[0], Integer.valueOf(split1[1]));
        }
    }


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        dynamicTableNameInnerInterceptor.setTableNameHandler((sql, tableName) -> {


            Integer integer = tableMap.get(tableName);
            if (integer != null) {
                // 获取参数方法
                Long id = RequestDataHelper.getRequestData();
                if(id == null){
                    throw new RuntimeException("未设置分表配置");
                }
                long l = Math.floorMod(id, Long.parseLong(integer.toString()));
                return tableName + "_" + l;
            }

            return tableName;
        });
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
        // 3.4.3.2 作废该方式
        // dynamicTableNameInnerInterceptor.setTableNameHandlerMap(map);
        return interceptor;
    }


}
