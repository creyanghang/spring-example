package com.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.entity.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: yh
 * @Description:
 * @date: 2022/6/6 12:24
 */

public interface UserMapper extends BaseMapper<UserPO> {
}
