package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

/**
 * Created by DELL on 2018/8/10.
 */
//具有感知beanfactory的能力。可以在实现类中获取到beanfactory。
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
