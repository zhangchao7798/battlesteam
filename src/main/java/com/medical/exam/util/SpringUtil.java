package com.medical.exam.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
* @author Wu
* @date 2018年5月16日
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }
    /**
     * get applicationContext
    * @author Wu
    * @date 2018年5月16日
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * get by name
    * @author Wu
    * @date 2018年5月16日
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * get by class
    * @author Wu
    * @date 2018年5月16日
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * get by name & class
    * @author Wu
    * @date 2018年5月16日
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}