package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * Created by DELL on 2018/6/20.
 */
public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanId);
    void registerBeanDefinition(String beanId, BeanDefinition bd);
}
