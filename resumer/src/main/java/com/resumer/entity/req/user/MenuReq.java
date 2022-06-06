package com.resumer.entity.req.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.print.attribute.standard.PrinterURI;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @description 菜单
 * @auth yh
 * @date 2022/5/29
 */
@Data
@ApiModel(description = "菜单对象")
@Accessors(chain = true)
public class MenuReq {
    @ApiModelProperty(value = "菜单名", required = true)
    @NotBlank(message = "menuName is null")
    private String menuName;

    @ApiModelProperty(value = "菜单名类型 auth:路由权限，web:web菜单", required = true)
    @Pattern(regexp = "auth|web", message = "menuType: The value can be auth or web")
    private String menuType;

    @ApiModelProperty(value = "路由地址", required = true)
    @NotBlank(message = "path is null")
    private String path;

    @ApiModelProperty(value = "资源父节点Id,0为没有父节点", required = false)
    private Long parentId;

    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    private Integer status;

    @ApiModelProperty(value = "菜单图标", required = false)
    private String icon;

    @ApiModelProperty(value = "排序, 越小越靠上，正序")
    private Integer sort;

    @ApiModelProperty(value = "备注", required = true)
    @NotBlank(message = "description is null")
    private String description;
}