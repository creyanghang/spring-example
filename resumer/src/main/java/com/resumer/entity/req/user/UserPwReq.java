package com.resumer.entity.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @description 用户密码登录
 * @auth yh
 * @date 2022/5/27
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "", description = "登录对象")
public class UserPwReq {
    @ApiModelProperty(value = "账号", notes = "账号", required = true)
    @NotBlank(message = "userName is null")
    private String userName;

    @ApiModelProperty(value = "密码", notes = "", required = true)
    @NotBlank(message = "passWord is null")
    private String passWord;

    @ApiModelProperty(value = "验证码唯一标识", required = true)
    @NotBlank(message = "sign is null")
    private String sign;

    @ApiModelProperty(value = "验证码", required = true)
    @NotBlank(message = "verify is null")
    private String verify;
}