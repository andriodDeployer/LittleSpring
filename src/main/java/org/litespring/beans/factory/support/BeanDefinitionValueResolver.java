package org.litespring.beans.factory.support;/**
 * Created by DELL on 2018/6/26.
 */

import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanFactory;

/**
 * user is
 **/


public class BeanDefinitionValueResolver {
    private BeanFactory beanFactory;

    public BeanDefinitionValueResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(PropertyValue propertyValue, BeanFactory beanFactory) {
        return propertyValue.resoveValue(propertyValue.getValue(),beanFactory);

    }
}
