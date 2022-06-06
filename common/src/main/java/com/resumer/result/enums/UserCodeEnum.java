package com.resumer.result.enums;

import com.resumer.result.ResultCodeInterface;

/**
 * @description 用户库异常定义
 * @auth yh
 * @date 2022/5/28
 */
public enum UserCodeEnum implements ResultCodeInterface {
    // 数据操作错误定义  1100-1199
    ROLE_EXIST(1006, "角色编码重复");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String msg;

    UserCodeEnum(int code, String resultMsg) {
        this.code = code;
        this.msg = resultMsg;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}