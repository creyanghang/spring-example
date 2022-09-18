package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Order;

/**
 * @description:
 * @author: yh
 * @date: 2022/9/18
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
