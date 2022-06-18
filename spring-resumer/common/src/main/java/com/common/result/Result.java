package com.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.common.result.enums.SysCodeEnum;

import java.io.Serializable;

/**
 * 统一结果返回数据封装类
 * @author  yh
 * @date  2022/5/27
 */
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private int code;

    private String msg;

    private T data;

    public Result() {
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static Result generate(int code, String message) {
        return generate(code, message, (Object)null);
    }
    public static <T> Result<T> generate(int code, String message, T data) {
        return new Result(code, message, data);
    }
    public static Result success(Object data) {
        return new Result(SysCodeEnum.SUCCESS.getCode(), SysCodeEnum.SUCCESS.getMsg(), data);
    }
    public static Result success() {
        return new Result(SysCodeEnum.SUCCESS.getCode(), SysCodeEnum.SUCCESS.getMsg(), null);
    }

    public static Result error(String msg) {
        return new Result(SysCodeEnum.UNSUCCESS.getCode(), SysCodeEnum.UNSUCCESS.getMsg(), msg);
    }
    public static Result error(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(ResultCodeInterface errorInfo) {
        Result rs = new Result();
        rs.setCode(errorInfo.getCode());
        rs.setMsg(errorInfo.getMsg());
        return rs;
    }
}