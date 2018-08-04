package org.litespring.aop.framework;/**
 * Created by DELL on 2018/8/4.
 */

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * user is lwb
 **/


public class ReflectiveMethodInvocation implements MethodInvocation{


    protected final  Object targetObject;
    protected final Method targetMethod;
    protected Object[] arguments;
    protected final List<MethodInterceptor> interceptors;

    private int currentIntercepterIndex = -1;

    public ReflectiveMethodInvocation(Object targetObject, Method targetMethod, Object[] arguments, List<MethodInterceptor> interceptors) {
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.arguments = arguments;
        this.interceptors = interceptors;
    }


    public Method getMethod() {
        return null;
    }

    public Object[] getArguments() {
        return new Object[0];
    }

    public Object proceed() throws Throwable {
        if(this.currentIntercepterIndex == interceptors.size()-1){
            return invokeJoinpoint();
        }
        this.currentIntercepterIndex ++;
        MethodInterceptor interceptor = this.interceptors.get(currentIntercepterIndex);
        return interceptor.invoke(this);
    }

    protected Object invokeJoinpoint() throws InvocationTargetException, IllegalAccessException {
        return this.targetMethod.invoke(targetObject,arguments);
    }

    public Object getThis() {
        return this.targetObject;
    }

    public AccessibleObject getStaticPart() {
        return null;
    }
}
