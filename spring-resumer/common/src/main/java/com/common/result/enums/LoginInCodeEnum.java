package com.common.result.enums;


import com.common.result.ResultCodeInterface;

/**
 * 登录异常定义
 * @author  yh
 * @date  2022/5/28
 */
public enum LoginInCodeEnum implements ResultCodeInterface {

    //1000-1099

    LOGIN_OUT(1001, "登录信息失效"),
    NOT_RESOURCE(1002, "无当前操作权限"),
    VERIFY_PAST_DUE(1003, "验证码已过期"),
    VERIFY_NO_MATCH(1004, "输入验证码错误"),
    USER_NO_FOUND(1005, "用户不存在")
    ;

    //处理结果码
    public Integer code;
    //结果描述
    public String msg;

    LoginInCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
