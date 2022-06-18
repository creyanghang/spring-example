package com.common.entity;

import lombok.Data;

/**
 * @description
 * @auth yh
 * @date 2022/5/25
 */
@Data
public class SysInfo {
    /**
     * IP
     */
    private String ip;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * token
     */
    private String token;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

}