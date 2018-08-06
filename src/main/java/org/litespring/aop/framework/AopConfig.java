package org.litespring.aop.framework;
/**
 * Created by DELL on 2018/8/6.
 */

import org.litespring.aop.Advice;

import java.lang.reflect.Method;
import java.util.List;

/**
 * user is
 **/


public interface AopConfig {
    Class<?> getTargetClass();
    Object getTargetObject();

    boolean isProxyTargetClass();
    Class<?>[] getProxiedInterfaces();
    boolean isInterfaceProxied(Class<?> intf);


    List<Advice> getAdvices();
    void addAdvice(Advice advice);
    List<Advice> getAdvices(Method method);
    void setTargetObject(Object obj);



}
