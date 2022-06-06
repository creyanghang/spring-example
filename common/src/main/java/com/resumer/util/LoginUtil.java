package com.resumer.util;


import com.alibaba.fastjson.JSON;
import com.resumer.cache.SysInfoSession;
import com.resumer.cache.UsersLoginSession;
import com.resumer.constant.LoginConst;
import com.resumer.entity.SysInfo;
import com.resumer.entity.UserLoginInfo;
import com.resumer.result.Result;
import com.resumer.result.enums.LoginInCodeEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录拦截方法
 * @author  yh
 * @date  2022/6/3
 */
@Slf4j
public class LoginUtil {



    /**
     * 获取系统公共信息
     *
     * @param request 请求提
     */
    public static SysInfo setSysInfo(HttpServletRequest request) {
        //获取系统公用信息
        SysInfo sysInfo = new SysInfo();
        sysInfo.setIp(getIp(request));
        //用户token
        sysInfo.setToken(request.getHeader(LoginConst.ACCESS_TOKEN));
        SysInfoSession.setSysInfo(sysInfo);

        return sysInfo;
    }



    /**
     * 获取系统公共信息
     */
    public static void setSysInfo(SysInfo sysInfo, UserLoginInfo loginInfoDTO) {
        sysInfo.setIp(loginInfoDTO.getIp());
        sysInfo.setRoleId(loginInfoDTO.getRoleId());

        sysInfo.setUserId(loginInfoDTO.getUserId());
        sysInfo.setUserName(loginInfoDTO.getUserName());
        sysInfo.setRoleCode(loginInfoDTO.getRoleCode());
        sysInfo.setRoleName(loginInfoDTO.getRoleName());

        SysInfoSession.setSysInfo(sysInfo);
    }

    /**
     * 获取ip
     *
     * @param request 请求提
     * @return String
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 登录异常处理
     * @param response 返回信息
     * @param codeEnum 错误信息
     */
    public static void loginOut(HttpServletResponse response, LoginInCodeEnum codeEnum) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        try {
            String s = JSON.toJSONString(Result.generate(codeEnum.code, codeEnum.msg));
            response.getWriter().print(JSON.parseObject(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info(codeEnum.msg);
        // 清除threadLoack ,防止拿到重复的信息
        UsersLoginSession.getContext().remove();
    }
}
