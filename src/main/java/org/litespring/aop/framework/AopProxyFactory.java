package org.litespring.aop.framework;/**
 * Created by DELL on 2018/8/6.
 */

/**
 * user is lwb
 **/


public interface AopProxyFactory {

    Object getProxy();
    Object getProxy(ClassLoader classLoader);

}
