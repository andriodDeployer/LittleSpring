package org.litespring.beans.factory.config;/**
 * Created by DELL on 2018/7/28.
 */

import org.litespring.beans.factory.BeanFactory;

/**
 * user is lwb
 **/


public interface AutowireCapableBeanFactory extends BeanFactory{
     Object resolveDependency(DependencyDescriptor descriptor);
}
