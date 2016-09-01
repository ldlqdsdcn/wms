package com.delmar.utils;

import java.lang.reflect.Field;

/**
 * Created by 刘大磊 on 2016/8/25.
 */
public class BeanHelper {
    public static Field[] getAllFields(Class cla)
    {
        return cla.getDeclaredFields();
    }

}
