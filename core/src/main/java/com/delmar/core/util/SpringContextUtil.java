package com.delmar.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by 刘大磊 on 2016/9/12 8:41.
 * Spring Util 工具类
 */
@Component
public class SpringContextUtil  implements ApplicationContextAware {
    private static ApplicationContext applicationContext; // Spring应用上下文环境
    /*
     * 实现了ApplicationContextAware 接口，必须实现该方法；
     *通过传递applicationContext参数初始化成员变量applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public  static <T> T getBean(String name, Class<T> requiredType)
    {
        return applicationContext.getBean(name, requiredType);
    }
    public  static <T> T getBean(Class<T> requiredType)
    {
        return applicationContext.getBean(requiredType);
    }
}
