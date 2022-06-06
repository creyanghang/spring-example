package com.resumer.util;


import com.resumer.constant.CharacterConstant;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 对象非空判断
 * @author  yh
 * @date  2022/5/27
 */
public class EmptyUtil {
    /**
     * 判断Object是否不为空,先判断是否不为null，为null返回false;不为null 继续判断toString()是否不为空
     */
    public static <T> boolean isNotEmpty(Object value) {
        return NullUtil.isNotNull(value) && !(EmptyUtil.isEmpty(value.toString()));
    }

    /**
     * 判断list是否不为空
     */
    public static <T> boolean isNotEmpty(List<T> lists) {
        return NullUtil.isNotNull(lists) && !(lists.isEmpty());
    }

    /**
     * 判断数组是否不为空
     */
    public static <T> boolean isNotEmpty(T[] datas) {
        return NullUtil.isNotNull(datas) && (datas.length > 0);
    }

    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotEmpty(String datas) {
        if (NullUtil.isNotNull(datas)) {
            if ("".equals(datas.trim())) {
                return false;
            } else if (StringUtils.isBlank(datas)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 判断Map是否不为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Map datas) {
        return MapUtils.isNotEmpty(datas);
    }



    /**
     * 判断字符串不能为空，且不能为“null”，主要是在js选择后验证，可以调用此方法
     */
    public static boolean isNotNullChars(String value) {
        return isNotEmpty(value) && !CharacterConstant.NULL.equals(value.trim().toLowerCase());
    }

    /**
     * 判断字符串不能为空，且不能为“undefined”，主要是在js选择后验证，可以调用此方法
     */
    public static boolean isNotUndefinedChars(String value) {
        return isNotEmpty(value) && !CharacterConstant.UNDEFINED.equals(value.trim().toLowerCase());
    }
    /**
     * 判断字符串不能为空，且不能为“null”和“undefined”，主要是在js选择后验证，可以调用此方法
     */
    public static boolean isNotEmptyChars(String value) {
        return isNotUndefinedChars(value) && isNotNullChars(value);
    }

    /**
     * 判断list是否为空
     */
    public static <T> boolean isEmpty(List<T> lists) {
        if (NullUtil.isNull(lists)) {
            return true;
        } else if (lists.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 判断数组是否为空
     */
    public static <T> boolean isEmpty(T[] datas) {
        if (NullUtil.isNull(datas)) {
            return true;
        } else if (datas.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String datas) {
        if (NullUtil.isNull(datas)) {
            return true;
        } else if ("".equals(datas.trim())) {
            return true;
        } else if (StringUtils.isBlank(datas)) {
            return true;
        }
        return false;
    }

    /**
     * 判断Map是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Map datas) {
        return MapUtils.isEmpty(datas);
    }

    /**
     * 判断字符串为空，或为“undefined”，主要是在js选择后验证，可以调用此方法
     */
    public static boolean isUndefinedChars(String value) {
        if (isEmpty(value)) {
            return true;
        } else if (CharacterConstant.UNDEFINED.equals(value.trim().toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串为空，或为“null”，主要是在js选择后验证，可以调用此方法
     */
    public static boolean isNullChars(String value) {
        if (isEmpty(value)) {
            return true;
        } else if (CharacterConstant.NULL.equals(value.trim().toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串为空，或为“null”和“undefined”，主要是在js选择后验证，可以调用此方法
     */
    public static boolean isEmptyChars(String value) {
        return isNullChars(value) || isUndefinedChars(value);
//		if (isNullChars(value) || isUndefinedChars(value)) {
//			return true;
//		}
//		return false;
    }

    /**
     * 判断Object是否不为空,先判断是否不为null，为null返回false;不为null 继续判断toString()是否不为空
     */
    public static <T> boolean isEmpty(Object value) {
        if (NullUtil.isNull(value)) {
            return true;
        } else if (EmptyUtil.isEmpty(value.toString())) {
            return true;
        }
        return false;
    }

    public static boolean allFieldIsEmpty(Object obj) {
        try {
            for (Field field : ReflectUtil.getAllFields(obj)) {
                //把私有属性公有化
                field.setAccessible(true);
                Object value = field.get(obj);
                if (EmptyUtil.isNotEmpty(value)) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }
}
