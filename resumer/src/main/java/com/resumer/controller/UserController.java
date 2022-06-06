package com.resumer.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.resumer.entity.req.user.UserReq;
import com.resumer.result.Result;
import com.resumer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description 用户相关操作
 * @auth yh
 * @date 2022/5/29
 */
@RestController
@RequestMapping(value = "/auth")
@Api(tags = "用户相关接口")
@ApiSupport(author = "鱼找水需要时间", order = 2)
@Validated
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 注册用户
     *
     * @param req 参数
     * @return Result
     * @author yh
     * @date 2022/5/29
     */
    @ApiOperation(value = "注册用户", httpMethod = "POST")
    @PostMapping(value = "/addUser")
    public Result addUser(@Valid UserReq req) {
        userService.addUser(req);
        return Result.success();
    }
}