package com.sample;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.sample.conf.RequestDataHelper;
import com.sample.entity.UserPO;
import com.sample.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author: yh
 * @Description:
 * @date: 2022/6/6 12:23
 */
@SpringBootTest
public class MybatisPlusApplicationTest {

    @Resource
    private UserMapper userMapper;


    /**
     * 分表测试
     *
     * @author yh
     * @date 2022/6/6
     */
    @Test
    public void test1() {
        // 分表测试
        RequestDataHelper.setRequestData(2L);
        UserPO userPO = userMapper.selectById(1);
        // 去观察sql， 数据库脚本在classpath下
    }

    /**
     * wrapper
     *
     * @author yh
     * @date 2022/6/6
     */
    @Test
    public void test2() {
        //https://blog.csdn.net/weixin_43847283/article/details/123019456
        // 查询
        LambdaQueryWrapper<UserPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPO::getName, "张三");
        RequestDataHelper.setRequestData(1L);
        userMapper.selectList(wrapper);

        // 链式更新
        LambdaUpdateChainWrapper<UserPO> wrapper1 = new LambdaUpdateChainWrapper<>(userMapper);
        RequestDataHelper.setRequestData(2L);
        wrapper1.eq(UserPO::getId, 1)
                .set(UserPO::getName, "李四")
                .update();
    }
}
