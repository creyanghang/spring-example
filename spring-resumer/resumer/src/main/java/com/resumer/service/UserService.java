package com.resumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.entity.UserLoginInfo;
import com.resumer.entity.po.user.Users;
import com.resumer.entity.req.user.UserPwReq;
import com.resumer.entity.req.user.UserReq;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
public interface UserService extends IService<Users> {

    /**
     * 用户密码登录
     *
     * @param req 参数
     * @return UserLoginInfo
     * @author yh
     * @date 2022/5/28
     */
    UserLoginInfo userLogin(UserPwReq req);


    /**
     * 获取用户信息
     *
     * @param userId   用户id
     * @param userName 用户名
     * @param passWord 密码
     * @param wechatId 微信号
     * @param email    邮箱
     * @return UserLoginInfo
     * @author yh
     * @date 2022/5/28
     */
    UserLoginInfo userLogin(Long userId, String userName, String passWord, String wechatId, String email);


    /**
     * 处理用户登录缓存
     *
     * @param userLoginInfo 用户信息
     * @author yh
     * @date 2022/5/28
     */
    void disposeCache(UserLoginInfo userLoginInfo, HttpServletRequest servletRequest);


    /**
     * 注册用户
     *
     * @param req 参数
     * @author yh
     * @date 2022/5/29
     */
    void addUser(UserReq req);
}
