package org.litespring.beans;/**
 * Created by DELL on 2018/6/19.
 */

import java.util.List;

/**
 * user is
 **/


public interface BeanDefinition {
    static final String SCOPE_SINGLETON = "singleton";
    static final String SCOPE_PROTOTYPE = "prototype";
    static final String SCOPE_DEFAULT = SCOPE_SINGLETON;
    String getBeanClassName() ;
    boolean hasBeanClass();
    Class<?> getBeanClass();
    Class<?> resoveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;

    boolean isSingleton();

    boolean isPrototype();

    String getScope();
    void setScope(String scope);
    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();
    String getID();

    boolean hasConstructorArgumentValues();
    boolean isSynthtic();
}
