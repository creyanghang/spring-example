package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.User;

/**
 * @description:
 * @author: yh
 * @date: 2022/9/18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
