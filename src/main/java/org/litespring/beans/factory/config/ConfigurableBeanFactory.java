package org.litespring.beans.factory.config;/**
 * Created by DELL on 2018/6/20.
 */

/**
 * user is lwb
 **/


public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);
    ClassLoader getBeanClassLoader();
}
