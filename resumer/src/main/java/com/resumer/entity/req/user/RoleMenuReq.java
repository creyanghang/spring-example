package com.resumer.entity.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @description 角色菜单权限
 * @auth yh
 * @date 2022/5/29
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "角色菜单权限")
public class RoleMenuReq {
    @ApiModelProperty(value = "角色id", required = true)
    @NotNull(message = "roleId is null")
    private Long roleId;

    @ApiModelProperty(value = "菜单id", required = true)
    @NotNull(message = "menuId is null")
    private Long menuId;
}