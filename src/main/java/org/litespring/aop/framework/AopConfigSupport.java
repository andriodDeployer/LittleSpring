package org.litespring.aop.framework;/**
 * Created by DELL on 2018/8/6.
 */

import org.litespring.aop.Advice;
import org.litespring.aop.PointCut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/


public class AopConfigSupport implements AopConfig {

    private boolean proxyTargetClass = false;
    private Object targetObject = null;
    private List<Advice> advices = new ArrayList<Advice>();
    private List<Class> interfacess = new ArrayList<Class>();

    public AopConfigSupport(){}

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Class<?> getTargetClass() {
        return targetObject.getClass();
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public Class<?>[] getProxiedInterfaces() {
        return new Class<?>[0];
    }

    public boolean isInterfaceProxied(Class<?> intf) {
        return false;
    }

    public List<Advice> getAdvices() {
        return advices;
    }

    public void addAdvice(Advice advice) {
        this.advices.add(advice);
    }

    //获取一个method上由哪些advice，或者说，由那些advice应用在这个advice上(遍历所有的advice，判断那个advice可以应用在这个method上，
    // 主要通过advice的绑定的(point-ref)pointcut,来判别是否满足execution表达式，从而判断出advice是否应用在method上)
    public List<Advice> getAdvices(Method method) {
        List<Advice> result = new ArrayList<Advice>();
        for(Advice advice : this.getAdvices()){
            PointCut pointCut = advice.getPointcut();
            if(pointCut.getMethodMatcher().matches(method)){
                result.add(advice);
            }
        }
        return result;
    }
}
