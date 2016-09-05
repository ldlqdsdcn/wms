package com.delmar.utils;

import java.lang.reflect.Method;

/**
 * @author 刘大磊 2014年12月22日 上午10:59:16
 */
public class CommonConverter {

    /**
     * 利用反射实现对象之间属性复制
     * @param from 拷贝对象
     * @param to  拷贝到的新对象
     */
    public static void copyProperties(Object from, Object to) {

        try {
            copyPropertiesExclude(from, to, null);
        } catch (Exception e) {
           throw new RuntimeException("对象参数复制异常");
        }
    }

    /**
     * 复制对象属性
     * @param from 从 from 对象
     * @param to   拷贝到 to 对象
     * @param excludeArray 排除属性列表
     * @throws Exception 抛出反射异常
     */
    public static void copyPropertiesExclude(Object from, Object to, String[] excludeArray) throws Exception {
       ProObjectUtil.copyPropertiesExclude(from,to,excludeArray);
    }


    /**
     * 从方法数组中获取指定名称的方法
     *
     * @param methods
     * @param name
     * @return
     */
    public static Method findMethodByName(Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName().equals(name))
                return method;
        }
        return null;
    }
}
