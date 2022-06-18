package com.resumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.resumer.entity.po.user.PicVerify;

/**
 * <p>
 * 图片验证码 服务类
 * </p>
 *
 * @author yh
 * @since 2022-05-27
 */
public interface PicVerifyService extends IService<PicVerify> {

    /**
     * 随机生成验证码
     *
     * @param sign 唯一标识
     * @return 验证码路径
     * @author yh
     * @date 2022/5/27
     */
    String queryPicVerify(String sign);


    /**
     * 验证验证码是否正确
     *
     * @param key1  缓存key1
     * @param key2  缓存key2
     * @param code 用户输入验证码
     * @author yh
     * @date 2022/5/28
     */
    void verifyCode(String key1, String key2, String code);
}
