package com.resumer.handler;

import com.resumer.exception.RemoteException;
import com.resumer.result.Result;
import com.resumer.result.enums.SysCodeEnum;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 定义全局异常处理器
 *
 * @author yh
 * @date 2022/5/27
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数不合法异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validExceptionHandler(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.joining());
        return Result.error(msg);
    }

    /**
     * POST缺少body参数
     **/
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result httpMessageNotReadableHandler(HttpMessageNotReadableException e) {
        return Result.error(SysCodeEnum.BODY_NOT_MATCH);
    }

    /**
     * 处理url参数异常
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constrainViolationHandler(ConstraintViolationException e) {
        String msg = e.getConstraintViolations().stream().map(item -> item.getMessage()).collect(Collectors.joining());
        return Result.error(msg);
    }

    /**
     * 未传入参数异常 @NotBlank
     **/
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterHandler(MissingServletRequestParameterException e) {
        return Result.error(SysCodeEnum.BODY_NOT_MATCH);
    }

    /**
     * 请求方式不正确
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedHandler(HttpRequestMethodNotSupportedException e) {
        return Result.error(SysCodeEnum.REQUEST_NOT_MATCH);
    }

    /**
     * 缺少请求参数
     **/
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException e) {
        return Result.error(SysCodeEnum.REQUEST_NOT_MATCH);
    }

    /**
     * 业务异常
     **/
    @ExceptionHandler(RemoteException.class)
    public Result remoteExceptionHandler(RemoteException e) {
        return Result.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        return Result.error(SysCodeEnum.INTERNAL_SERVER_ERROR);
    }
}

