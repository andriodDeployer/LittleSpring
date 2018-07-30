package org.litespring.beans.factory.config;

import org.litespring.beans.BeansException;

/**
 * Created by DELL on 2018/7/30.
 */
public interface BeanPostProcessor {
    //初始化前(对象已经创建过了)
    Object beforeInitialization(Object bean, String beanName) throws BeansException;

    Object afterInitializeation(Object bean, String beanName) throws BeansException;

}
