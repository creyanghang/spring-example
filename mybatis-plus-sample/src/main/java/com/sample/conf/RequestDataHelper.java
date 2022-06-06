package com.sample.conf;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.Map;

/**
 * 请求参数传递辅助类
 */
public class RequestDataHelper {
    /**
     * 请求参数存取
     */
    private static final ThreadLocal<Long> REQUEST_DATA = new ThreadLocal<>();

    /**
     * 设置请求参数
     *
     * @param requestData 请求参数 Long
     */
    public static void setRequestData(Long requestData) {
        REQUEST_DATA.set(requestData);
    }

    /**
     * 获取请求参数
     *
     * @param param 请求参数
     * @return 请求参数 MAP 对象
     */
    public static <T> T getRequestData(String param) {
        Long id = getRequestData();
        if (null != id) {
            return (T) id;
        }
        return null;
    }

    /**
     * 获取请求参数
     *
     * @return 请求参数 MAP 对象
     */
    public static Long getRequestData() {
        return REQUEST_DATA.get();
    }
}
