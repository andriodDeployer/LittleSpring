package org.litespring.beans.factory.support;/**
 * Created by DELL on 2018/6/26.
 */

import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;

/**
 * user is
 **/


public class BeanDefinitionValueResolver {
    private BeanFactory beanFactory;

    public BeanDefinitionValueResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if(value instanceof RuntimeBeanReference){
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            String refName = reference.getBeanName();
            Object bean = beanFactory.getBean(refName);
            return bean;
        }else if(value instanceof TypedStringValue){
            return ((TypedStringValue)value).getValue();
        }else{
            throw new RuntimeException("the value ' "+value+" ' has not implemented ");
        }
    }
}
