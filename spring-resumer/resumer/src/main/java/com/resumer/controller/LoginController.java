package com.resumer.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.common.annotation.Unimpeded;
import com.common.constant.RedisUserConst;
import com.common.entity.UserLoginInfo;
import com.resumer.entity.req.user.UserPwReq;
import com.common.result.Result;
import com.resumer.service.PicVerifyService;
import com.resumer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @description
 * @auth yh
 * @date 2022/5/24
 */
@RestController
@RequestMapping(value = "/login")
@Api(tags = "登录接口")
@ApiSupport(author = "鱼找水需要时间", order = 2)
@Validated
@AllArgsConstructor
public class LoginController {
    private final PicVerifyService picVerifyService;
    private final UserService userService;

    @Unimpeded
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @PostMapping(value = "/userlogin")
    public Result userLogin(@Valid UserPwReq userPwReq, HttpServletRequest servletRequest) {
        // 验证验证码是否正确
        picVerifyService.verifyCode(RedisUserConst.VERIFY_KEY, userPwReq.getSign(), userPwReq.getVerify());

        // 获取用户信息
        UserLoginInfo userLoginInfo = userService.userLogin(userPwReq);

        // 处理缓存
        userService.disposeCache(userLoginInfo, servletRequest);

        return Result.success(userLoginInfo);
    }
}