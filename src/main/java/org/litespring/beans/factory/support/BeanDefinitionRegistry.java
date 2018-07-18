package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * Created by DELL on 2018/6/20.
 */
public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanId);//根据beanid获取BeanDefinition
    void registerBeanDefinition(String beanId, BeanDefinition bd);//将beanId和这个beanId对应的beandefinition，存放起来。具体存放到哪里，这个我们是不不关心的，有实现类来决定，我们之关系，存放之后，通过beanid能获取到这个beanDefinition。
}
