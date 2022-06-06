package com.resumer.interceptor;

import com.resumer.annotation.ResourcesPass;
import com.resumer.annotation.Unimpeded;
import com.resumer.cache.SysInfoSession;
import com.resumer.cache.UsersLoginSession;
import com.resumer.constant.LoginConst;
import com.resumer.entity.SysInfo;
import com.resumer.entity.UserLoginInfo;
import com.resumer.result.enums.LoginInCodeEnum;
import com.resumer.util.EmptyUtil;
import com.resumer.util.LoginUtil;
import com.resumer.util.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description
 * @auth yh
 * @date 2022/5/27
 */
@Slf4j
@Aspect
public class ResumerInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisCache redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //获取系统公共信息
        SysInfo sysInfo = LoginUtil.setSysInfo(request);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 如果有当前注解 , 则不需要校验token
        boolean hasMethodAnnotation = handlerMethod.hasMethodAnnotation(Unimpeded.class);
        if (hasMethodAnnotation) {
            // 清除threadLoack ,防止拿到重复的信息
            UsersLoginSession.getContext().remove();
            return true;
        }

        try {
            // 从请求头部获取 登录 token
            String token = SysInfoSession.getToken();
            if (StringUtils.isBlank(token)) {
                // 登录信息失效，无token
                LoginUtil.loginOut(response, LoginInCodeEnum.LOGIN_OUT);
                return false;
            }
            UserLoginInfo loginInfoDTO = this.redisCache.getValue(LoginConst.USER_LOGIN_REDIS_KEY, token);
            if (EmptyUtil.isEmpty(loginInfoDTO)) {
                // redis 中没有用户信息
                LoginUtil.loginOut(response, LoginInCodeEnum.LOGIN_OUT);
                return false;
            }
            //是否需要验证权限
            boolean resourcesPassFlag = handlerMethod.hasMethodAnnotation(ResourcesPass.class);
            if (!resourcesPassFlag) {
                // 判断是否有对应的资源
                String thisUri = request.getRequestURI();
                //用户角色下的权限
                Set<String> haveUri = redisCache.getValue(LoginConst.USER_ROLE_AUTH_KEY, token);

                if (EmptyUtil.isEmpty(haveUri) || !haveUri.contains(thisUri)) {
                    // 用户没有该权限
                    LoginUtil.loginOut(response, LoginInCodeEnum.NOT_RESOURCE);
                    return false;
                }
                //更新权限过期时间
                this.redisCache.expire(LoginConst.USER_ROLE_AUTH_KEY, token, LoginConst.USER_TOKEN_HOLD_MINUTE, TimeUnit.MINUTES);
            }

            //更新登录过期时间
            this.redisCache.expire(LoginConst.USER_LOGIN_REDIS_KEY, token, LoginConst.USER_TOKEN_HOLD_MINUTE, TimeUnit.MINUTES);

            //更新登录过期时间
            redisCache.expire(LoginConst.LOGIN_USER_TOKEN, String.valueOf(loginInfoDTO.getUserId()),
                    LoginConst.USER_TOKEN_HOLD_MINUTE, TimeUnit.MINUTES);

            // 放入ThreadLocal 中
            UsersLoginSession.getContext().set(loginInfoDTO);

            //获取系统公共信息
            LoginUtil.setSysInfo(sysInfo, loginInfoDTO);
        } catch (Throwable e) {
            log.error(request.getServletPath() + " , occurs exception  :" + e, e);
        }
        return true;
    }
}