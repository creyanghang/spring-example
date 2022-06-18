package com.common.util;

/**
 * 对null的公共方法
 *
 * @author yh
 * @date 2022/5/27
 */
public class NullUtil {
    /**
     * 判断参数obj是否为null，如果obj为null,返回false；不为null，返回true
     */
    public static boolean isNotNull(Object obj) {
        return null != obj;
    }

    /**
     * 判断参数obj是否为null，如果obj为null,返回true；不为null，返回false
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }
}
