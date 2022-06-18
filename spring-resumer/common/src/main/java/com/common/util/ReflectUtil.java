package com.common.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 类反射工具类
 * @author  yh
 * @date  2022/5/27
 */
public class ReflectUtil {

    /**
     * 只获取本类的所有属性
     *
     * @param obj
     * @return
     */
    public static Field[] getFields(Object obj) {
        return obj.getClass().getDeclaredFields();
    }

    /**
     * 只获取本类的所有属性
     *
     * @param clazz
     * @return
     */
    public static Field[] getFields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    /**
     * 获取本类及所有父类的所有属性，不包括Object类的属性
     *
     * @param obj
     * @return
     */
    public static Field[] getAllFields(Object obj) {
        return getAllFieldsByCutOff(obj, Object.class);
    }

    /**
     * 获取本类及所有父类的所有属性，不包括Object类的属性
     *
     * @param clazz
     * @return
     */
    public static Field[] getAllFields(Class<?> clazz) {
        return getAllFieldsByCutOff(clazz, Object.class);
    }


    /**
     * 获取本类及父类的所有属性，这个父类获取到指定的类（cutOffClass），不包括cutOffClass类的属性
     *
     * @param obj
     * @param cutOffClass 指定的父类
     * @return
     */
    public static Field[] getAllFieldsByCutOff(Object obj, Class<?> cutOffClass) {
        Class<?> clazz = obj.getClass();
        return getAllFieldsByCutOff(clazz, cutOffClass);
    }

    /**
     * 获取本类及父类的所有属性，这个父类获取到指定的类（cutOffClass），不包括cutOffClass类的属性
     *
     * @param clazz
     * @param cutOffClass 指定的父类
     * @return
     */
    public static Field[] getAllFieldsByCutOff(Class<?> clazz, Class<?> cutOffClass) {
        Field[] fields = getFields(clazz);
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != cutOffClass) {
            for (; superClass != cutOffClass; superClass = superClass.getSuperclass()) {
                Field[] fields1 = superClass.getDeclaredFields();
                fields = (Field[]) ArrayUtils.addAll(fields, fields1);
            }
        }
        return fields;
    }


    /**
     * 根据字段名获取属性，包含父类属性
     *
     * @param obj
     * @param fieldName
     * @return
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {//该处不需要处理
            }
        }
        return null;
    }

    /**
     * Obj fieldName
     *
     * @param obj
     * @param fieldName
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getValueByFieldName(Object obj, String fieldName)
            throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * obj fieldName
     *
     * @param obj
     * @param fieldName
     * @param value
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setValueByFieldName(Object obj, String fieldName,
                                           Object value) throws SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        if (field.isAccessible()) {
            field.set(obj, value);
        } else {
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
        }
    }

    public static void setValueByFieldName(Object obj, Map<String, Object> valueMap) throws SecurityException,
            IllegalArgumentException, IllegalAccessException {
        if (EmptyUtil.isNotEmpty(valueMap)) {
            for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
                setValueByFieldName(obj, entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * 判断一个类是否拥有某个属性字段
     *
     * @param clazz
     * @param fieldName
     * @return
     */
    public static boolean hasField(Class<?> clazz, String fieldName) {
        Field[] allFields = getAllFields(clazz);
        if (EmptyUtil.isNotEmpty(allFields)) {
            for (Field field : allFields) {
                if (field.getName().equals(fieldName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据一个注解获得一个类中标识这个注解的字段的值，只获取一个
     *
     * @param obj
     * @param annotationClass
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getValueByAnnotation(Object obj, Class<?> annotationClass) {
        Object value = null;
        try {
            Field[] fields = getAllFields(obj);
            for (Field field : fields) {
                boolean find = false;
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType() == annotationClass) {
                        value = getValueByFieldName(obj, field.getName());
                        find = true;
                        break;
                    }
                }
                if (find) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String[] getFieldNameByAnnotation(Class<?> clazz, Class<?> annotationClass) {
        List<String> fieldNames = Lists.newArrayList();
        try {
            Field[] fields = getAllFields(clazz);
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType() == annotationClass) {
                        fieldNames.add(field.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EmptyUtil.isNotEmpty(fieldNames) ? (String[]) fieldNames.toArray(new String[fieldNames.size()]) : null;
    }


    public static Map<String, Object> getFieldAndValueByAnnotation(Object object, Class<?> annotationClass) {
        Map<String, Object> map = new TreeMap<String, Object>();
        try {
            Field[] fields = getAllFields(object);
            if (EmptyUtil.isEmpty(fields)) {
                return null;
            }
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType() == annotationClass) {
                        String fieldName = field.getName();
                        Object value = ReflectUtil.getValueByFieldName(object, fieldName);
                        if (EmptyUtil.isNotEmpty(value)) {
                            map.put(fieldName, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static Class<?> getFieldTypeByAnnotation(Class<?> clazz, Class<?> annotationClass) {
        try {
            Field[] fields = getAllFields(clazz);
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation.annotationType() == annotationClass) {
                        return field.getType();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> T createInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T createInstanceAndSetValue(Class<T> cls, Map<String, Object> valueMap) {
        T obj = createInstance(cls);
        if (EmptyUtil.isNotEmpty(obj)) {
            try {
                setValueByFieldName(obj, valueMap);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return obj;
    }

    public static <T> T createInstanceAndSetValue(Class<T> cls, String fieldName, Object value) {
        T obj = createInstance(cls);
        if (EmptyUtil.isNotEmpty(obj)) {
            try {
                setValueByFieldName(obj, fieldName, value);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return obj;
    }
}
