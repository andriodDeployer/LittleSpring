package org.litespring.beans.factory.config;

import org.litespring.beans.BeansException;

/**
 * Created by DELL on 2018/7/30.
 */

//实例化：创建对象，初始化：对创建好的对象的属性进行赋值。
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    //对象实例化之前
    Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    boolean afterInstantiation(Object bean, String beanName) throws BeansException;

    void postProcessorPropertyValues(Object bean, String beanName) throws BeansException;

}
