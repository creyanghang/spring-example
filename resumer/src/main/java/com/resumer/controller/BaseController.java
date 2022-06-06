package com.resumer.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.resumer.annotation.Unimpeded;
import com.resumer.result.Result;
import com.resumer.service.PicVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @description 公共方法
 * @auth yh
 * @date 2022/5/27
 */
@RestController
@RequestMapping(value = "/base")
@Api(tags = "公共接口")
@ApiSupport(author = "鱼找水需要时间", order = 1)
@Validated
@Slf4j
public class BaseController {

    @Resource
    private PicVerifyService picVerifyService;

    @Unimpeded
    @ApiOperation(value = "获取图片验证码", httpMethod = "GET")
    @GetMapping(value = "/queryPicVerify")
    public Result queryPicVerify(@ApiParam(name = "sign", value = "唯一标识", required = true)
                                 @NotBlank(message = "sign is null")
                                 @RequestParam("sign") String sign) {
        return Result.success(picVerifyService.queryPicVerify(sign));
    }
}