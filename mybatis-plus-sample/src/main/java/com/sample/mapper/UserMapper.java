package com.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sample.entity.User;

/**
 * @author: yh
 * @Description: 支持不需要 UserMapper.xml 这个模块演示内置 CRUD 咱们就不要 XML 部分了
 * @date: 2022/6/6 12:24
 */

public interface UserMapper extends BaseMapper<User> {
}
