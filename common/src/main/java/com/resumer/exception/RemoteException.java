package com.resumer.exception;

import com.resumer.result.ResultCodeInterface;

/**
 * @description 服务异常类
 * @auth yh
 * @date 2022/5/27
 */
public class RemoteException extends BaseException {

    public RemoteException(ResultCodeInterface codeInterface, String message) {
        super(codeInterface.getCode(), codeInterface.getMsg() + ":" + message);
    }

    public RemoteException(String msg) {
        super(msg);
    }

    public RemoteException(Exception e) {
        super(e);
    }

    public RemoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public RemoteException(ResultCodeInterface codeInterface) {
        super(codeInterface.getCode(), codeInterface.getMsg());
    }

    public RemoteException(int code, String msg, Object data) {
        super(code, msg, data);
    }
}