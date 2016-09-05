package com.delmar.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘大磊 2014年12月22日 上午10:59:16
 */
public class CommonConverter {

    /**
     * 对象转换，把对象相同名字的属性复制给新对象
     * @param obj 要转换的对象
     * @param classOfT 转换后的类
     * @return 转换后类的实例
     */
    public static <T> T convertObject(Object obj, Class<T> classOfT) {
        T toObject;
        try {
            toObject = classOfT.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("转换对象-实例化目标对象失败", e);
        }
        try {
            BeanUtils.copyProperties(toObject, obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("转换对象-复制属性失败", e);
        }
        return toObject;
    }

    /**
     * 把对象转换为Map
     * @param obj 要转换的对象
     * @return 返回HashMap实例
     */
    public static Map convertObjectToMap(Object obj)
    {
        Map<String,Object> result=new HashMap<>();
        try {
            BeanUtils.copyProperties(result,obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
           throw new RuntimeException("把对象转换为Map异常", e);
        }
        return result;
    }
}
