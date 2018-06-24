package org.litespring.beans.factory.config;

/**
 * Created by DELL on 2018/6/20.
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);
    Object getSingleton(String beanName);
}
