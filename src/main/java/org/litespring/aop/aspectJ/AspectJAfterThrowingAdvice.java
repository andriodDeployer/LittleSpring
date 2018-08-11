package org.litespring.aop.aspectJ;/**
 * Created by DELL on 2018/8/4.
 */

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

/**
 * user is lwb
 **/


public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice{


    public AspectJAfterThrowingAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, AspectInstanceFactory aspectInstanceFactory) {
        super(adviceMethod, pointcut, aspectInstanceFactory);
    }

    public Object invoke(MethodInvocation mi) throws Throwable {
        try{
            return mi.proceed();
        }catch (Throwable t){
            invokeAdviceMethod();
            throw t;
        }
    }
}
