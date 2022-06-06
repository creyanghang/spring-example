package com.resumer.cache;

import com.resumer.entity.UserLoginInfo;
import lombok.Data;

/**
 * 用户登录信息缓存
 * @author  yh
 * @date  2022/6/3
 */
@Data
public class UsersLoginSession {

    private static ThreadLocal<UserLoginInfo> context = new ThreadLocal<UserLoginInfo>();

    public static ThreadLocal<UserLoginInfo> getContext() {
        return context;
    }

    public static UserLoginInfo getLoginInfo() {
        return context.get();
    }

    public static void delLoginInfo() {
        context.remove();
    }

    public static String getUserId() {
        UserLoginInfo loginInfoDTO = context.get();
        if (loginInfoDTO == null) {
            return null;
        } else {
            return loginInfoDTO.getUserId().toString();
        }
    }

    public static String getToken() {
        UserLoginInfo loginInfoDTO = context.get();
        if (loginInfoDTO == null) {
            return null;
        } else {
            return loginInfoDTO.getToken();
        }
    }
}
