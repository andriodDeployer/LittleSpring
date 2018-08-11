package org.litespring.beans.factory;
/**
 * Created by DELL on 2018/6/19.
 */

import java.util.List;

/**
 * user is
 **/


public interface BeanFactory {
    //根据beanId获取对应的bean
    Object getBean(String beanId);
    //根据beanName，获取到这个bean的类型
    Class<?> getType(String name) throws NoSuchBeanDefinitionException;
    List<Object> getBeansByType(Class clazz);

}
