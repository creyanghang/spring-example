package com.resumer.entity.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @description
 * @auth yh
 * @date 2022/5/28
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "角色")
public class RoleReq {
    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "name is null")
    private String name;

    @ApiModelProperty(value = "角色code", notes = "admin、user等", example = "", required = true)
    @NotBlank(message = "roleKey is null")
    private String roleKey;

    @ApiModelProperty(value = "角色状态（0正常 1停用）", required = true)
    @Min(0)
    @Max(1)
    @NotNull(message = "status is null")
    private Integer status;

    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message = "description is null")
    private String description;
}