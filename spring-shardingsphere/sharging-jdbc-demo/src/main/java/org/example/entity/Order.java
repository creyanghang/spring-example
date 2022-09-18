package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单类
 * @author:  yh
 * @date:  2022/9/18
 */
@TableName("t_order")//逻辑表名
@Data
public class Order {

    //当配置了shardingsphere-jdbc的分布式序列时，自动使用shardingsphere-jdbc的分布式序列
    //当没有配置shardingsphere-jdbc的分布式序列时，自动依赖数据库的主键自增策略
    @TableId(type = IdType.AUTO)
    private Long id;

    //自动映射数据库表中的order_no字段
    private String orderNo;

    //user_id
    private Long userId;

    private BigDecimal amount;
}
