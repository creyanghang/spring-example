package com.common.result;

/**
 * 异常信息描述基础信息接口类
 *
 * @author yh
 * @date 2022/5/27
 */
public interface ResultCodeInterface {
    /**
     * 错误描述
     */
    String getMsg();

    /**
     * 错误码
     */
    Integer getCode();
}