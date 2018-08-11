package org.litespring.aop.aspectJ;/**
 * Created by DELL on 2018/8/4.
 */

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.PointCut;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

/**
 * user is lwb
 **/


public abstract class AbstractAspectJAdvice implements Advice{


    private Method adviceMethod;
    private PointCut pointCut;
    private AspectInstanceFactory aspectInstanceFactory;

    public AbstractAspectJAdvice(Method adviceMethod,AspectJExpressionPointcut pointcut,AspectInstanceFactory aspectInstanceFactory){
        this.adviceMethod = adviceMethod;
        this.pointCut = pointcut;
        this.aspectInstanceFactory = aspectInstanceFactory;
    }

    public abstract Object invoke(MethodInvocation mi) throws Throwable;

    public PointCut getPointcut() {
        return pointCut;
    }

    public void invokeAdviceMethod() throws Throwable{
        adviceMethod.invoke(aspectInstanceFactory.getAspectInstance());
    }

    public Method getAdviceMethod() {
        return adviceMethod;
    }
}
