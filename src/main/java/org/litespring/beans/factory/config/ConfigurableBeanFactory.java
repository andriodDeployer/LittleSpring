package org.litespring.beans.factory.config;/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.beans.factory.BeanFactory;

/**
 * user is lwb
 **/


public interface ConfigurableBeanFactory extends BeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);
    ClassLoader getBeanClassLoader();

}
