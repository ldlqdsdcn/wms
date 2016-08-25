package com.delmar.utils;

import java.lang.reflect.Field;

/**
 * Created by admin on 2016/8/25.
 */
public class BeanHelper {
    public static Field[] getAllFields(Class cla)
    {
        Field[] fields = cla.getDeclaredFields();
        return fields;
    }

}
