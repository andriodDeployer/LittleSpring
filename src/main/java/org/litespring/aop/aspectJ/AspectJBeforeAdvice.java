package org.litespring.aop.aspectJ;/**
 * Created by DELL on 2018/8/4.
 */


import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * user is lwb
 **/


public class AspectJBeforeAdvice extends AbstractAspectJAdvice{

    //aspectJExpressionPointcut：确定了在哪些方法上执行增强。Method：确定了增强的逻辑。adviceObject：method在哪个对象上调用
    public AspectJBeforeAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, Object adviceObject) {
        super(adviceMethod, pointcut, adviceObject);
    }

    //表示其逻辑(adviceMethod.invoke)要在其后面所有的advice前执行。mi.procced表示按照某种顺序执行当前advice后面所有的advice。具体按照什么顺序，当前advice并不关系，因为当前的advice的逻辑已经执行过了。因为实际逻辑在最后一个advice执行完之后执行。所以，所有的beforeadvice在实际逻辑之前执行。
    public Object invoke(MethodInvocation mi) throws Throwable{

        //adviceMethod.invoke(adviceObject);尽量不要直接使用的属性，要用就用get方法。
        invokeAdviceMethod();
        Object o = mi.proceed();//执行真正的方法
        return o;
    }


}
