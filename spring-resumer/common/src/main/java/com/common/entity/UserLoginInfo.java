package com.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户基本信息
 * @author  yh
 * @date  2022/6/3
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "UserLoginInfo对象", description = "用户登录信息")
public class UserLoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "角色id")
    private Long roleId;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String headPhoto;

    @ApiModelProperty(value = "性别: 男-male 女-famale")
    private String gender;

    @ApiModelProperty(value = "ip地址")
    private String ip;
}
