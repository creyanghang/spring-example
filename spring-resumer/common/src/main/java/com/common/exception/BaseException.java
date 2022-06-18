package com.common.exception;

import com.common.result.ResultCodeInterface;

/**
 * 自定义异常类，标识业务系统出现的异常信息
 *
 * @author yh
 * @date 2022/5/27
 */
public class BaseException extends RuntimeException {
    private int code;
    private String msg;
    private Object data;

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(Exception e) {
        this(e.getMessage());
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseException(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseException(ResultCodeInterface e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
