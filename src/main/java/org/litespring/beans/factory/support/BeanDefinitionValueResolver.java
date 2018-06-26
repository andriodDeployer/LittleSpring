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
            //todo :这里这样做是否违反了ocp原则，但是如果针对每种情况做扩展的话，是否类太多，这个怎么权衡？
            throw new RuntimeException("the value ' "+value+" ' has not implemented ");
        }
    }
}
