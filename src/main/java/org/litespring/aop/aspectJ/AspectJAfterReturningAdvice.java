package org.litespring.aop.aspectJ;/**
 * Created by DELL on 2018/8/4.
 */

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.PointCut;

import java.lang.reflect.Method;

/**
 * user is lwb
 **/


public class AspectJAfterReturningAdvice implements Advice{
    private Method adviceMethod;
    private PointCut pointcut;
    private Object adviceObject;

    public AspectJAfterReturningAdvice(Method adviceMethod, PointCut pointcut, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }

    public PointCut getPointcut() {
        return pointcut;
    }
    //其实这是一个递归调用，很复杂，但是只要理解了语义，就很好理解了。
    //表示其逻辑(adviceMethod.invoke)要在其后面所有的advice后执行。mi.procced表示按照某种顺序执行当前advice后面所有的advice。具体按照什么顺序，当前advice并不关心，因为当前的advice的逻辑要在他们所有的逻辑执行完之后再会执行，因为真实的逻辑，会在最后一个advice执行完之后执行。所以after的执行刚好在世纪逻辑执行完之后开始执行。
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object o = invocation.proceed();
        adviceMethod.invoke(adviceObject);
        return o;
    }
}
