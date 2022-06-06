package com.resumer.entity.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.PipedReader;

/**
 * @description
 * @auth yh
 * @date 2022/5/29
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "用户对象")
public class UserReq {
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "userName is null")
    private String userName;

    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message = "nickName is null")
    private String nickName;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "passWord is null")
    private String passWord;

    @ApiModelProperty(value = "性别: 0男，1女，2未知，默认未知", required = false, notes = "")
    @Min(0)
    @Max(2)
    private Integer sex = 2;

    @ApiModelProperty(value = "角色id", required = false)
    private Long roleId;
}