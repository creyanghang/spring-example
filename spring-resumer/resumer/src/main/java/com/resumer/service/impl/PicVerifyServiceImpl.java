package com.resumer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.constant.RedisUserConst;
import com.common.exception.RemoteException;
import com.common.result.enums.LoginInCodeEnum;
import com.common.util.EmptyUtil;
import com.common.util.RedisCache;
import com.resumer.constant.HostConst;
import com.resumer.entity.po.user.PicVerify;
import com.resumer.mapper.user.PicVerifyMapper;
import com.resumer.service.PicVerifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 图片验证码 服务实现类
 * </p>
 *
 * @author yh
 * @since 2022-05-27
 */
@Service
public class PicVerifyServiceImpl extends ServiceImpl<PicVerifyMapper, PicVerify> implements PicVerifyService {

    @Resource
    private RedisCache redisCache;

    /**
     * 随机生成验证码
     *
     * @param sign 唯一标识
     * @return 验证码路径
     * @author yh
     * @date 2022/5/27
     */
    @Override
    public String queryPicVerify(String sign) {
        int id = (int) (1 + Math.random() * (1000 - 1 + 1));
        PicVerify picVerify = baseMapper.selectById(id);
        if (EmptyUtil.isEmpty(picVerify)) {
            return "";
        }
        String code = picVerify.getVerifyCode();
        redisCache.set(RedisUserConst.VERIFY_KEY, sign, code, 60);
        return HostConst.PIC_VERIFY + picVerify.getVerifyFileName();
    }

    /**
     * 验证验证码是否正确
     *
     * @param key1 缓存key1
     * @param key2 缓存key2
     * @param code 用户输入验证码
     * @author yh
     * @date 2022/5/28
     */
    @Override
    public void verifyCode(String key1, String key2, String code) {
        String key = key1;
        if (EmptyUtil.isNotEmpty(key2)) {
            key = key + ":" + key2;
        }
        String verify = redisCache.getValue(key);
        if (EmptyUtil.isEmpty(verify)) {
            // 验证码过期
            throw new RemoteException(LoginInCodeEnum.VERIFY_PAST_DUE);
        }
        if (!verify.equals(code)) {
            // 验证码不匹配
            throw new RemoteException(LoginInCodeEnum.VERIFY_NO_MATCH);
        }
        redisCache.removeValue(key);
    }
}
