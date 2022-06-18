package com.common.cache;

import com.common.entity.SysInfo;

/**
 * @description 获取用户信息
 * @auth yh
 * @date 2022/5/25
 */
public class SysInfoSession {

    private static ThreadLocal<SysInfo> context = new ThreadLocal<SysInfo>();

    public static ThreadLocal<SysInfo> getContext() {
        return context;
    }

    public static SysInfo getSysInfo() {
        return context.get();
    }

    public static void setSysInfo(SysInfo info) {
        context.set(info);
    }

    public static void delSysInfo() {
        context.remove();
    }

    public static String getIp() {
        SysInfo sysInfo = context.get();
        if (sysInfo == null) {
            return null;
        } else {
            return sysInfo.getIp();
        }
    }


    public static Long getRoleId() {
        SysInfo sysInfo = context.get();
        if (sysInfo == null) {
            return null;
        } else {
            return sysInfo.getRoleId();
        }
    }
    public static String getToken() {
        SysInfo sysInfo = context.get();
        if (sysInfo == null) {
            return null;
        } else {
            return sysInfo.getToken();
        }
    }

    public static Long getUserId() {
        SysInfo sysInfo = context.get();
        if (sysInfo == null) {
            return null;
        } else {
            return sysInfo.getUserId();
        }
    }
}