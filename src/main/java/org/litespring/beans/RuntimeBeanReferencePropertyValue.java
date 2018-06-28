package org.litespring.beans;/**
 * Created by DELL on 2018/6/28.
 */

import org.litespring.beans.factory.BeanFactory;

/**
 * user is lwb
 **/


public class RuntimeBeanReferencePropertyValue extends PropertyValue {

    public RuntimeBeanReferencePropertyValue(String name, String value) {
        super(name, value);
    }

    public Object resoveValue( BeanFactory beanFactory) {
        return beanFactory.getBean(getValue());
    }
}
