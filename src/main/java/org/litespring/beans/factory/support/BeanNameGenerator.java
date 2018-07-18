package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;

/**
 * Created by DELL on 2018/7/18.
 */
public interface BeanNameGenerator {

    //给指定的BeanDefinition产生一个beanName
    String generateBeanName(BeanDefinition definition,BeanDefinitionRegistry registry);
}
