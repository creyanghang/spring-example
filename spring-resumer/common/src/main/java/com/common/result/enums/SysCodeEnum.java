package com.common.result.enums;

import com.common.result.ResultCodeInterface;

/**
 * @description 系统异常枚举
 * @auth yh
 * @date 2022/5/27
 */
public enum SysCodeEnum implements ResultCodeInterface {
    // 数据操作错误定义  1000-1100
    SUCCESS(200, "成功!"),
    UNSUCCESS(400, "失败"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配!"),
    BODY_NOT_MATCH(402, "请求的数据格式不符!"),
    REQUEST_NOT_MATCH(403, "请求不合法"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!");

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String msg;

    SysCodeEnum(Integer code, String resultMsg) {
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