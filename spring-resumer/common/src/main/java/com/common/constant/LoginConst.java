package com.common.constant;

/**
 * @description 登录常量
 * @auth yh
 * @date 2022/5/28
 */
public class LoginConst {
    /**
     * 请求头token
     */
    public static final String ACCESS_TOKEN = "token";

    /**
     * redis 用户token key
     */
    public static final String LOGIN_USER_TOKEN = "login_user_token";

    /**
     * redis 用户登录信息
     */
    public static final String USER_LOGIN_REDIS_KEY = "user_login_info";

    /**
     * redis 用户角色权限
     */
    public static final String USER_ROLE_AUTH_KEY = "user_role_auth_key";

    /**
     * 用户token过期时间 分钟
     */
    public static final Integer USER_TOKEN_HOLD_MINUTE = 60;
}