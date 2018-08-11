package org.litespring.test.v5;/**
 * Created by DELL on 2018/8/4.
 */

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectJ.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectJ.AspectJAfterThrowingAdvice;
import org.litespring.aop.aspectJ.AspectJBeforeAdvice;
import org.litespring.aop.aspectJ.AspectJExpressionPointcut;
import org.litespring.aop.config.AspectInstanceFactory;
import org.litespring.aop.framework.ReflectiveMethodInvocation;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.service.v5.PetStoreService;
import org.litespring.util.MessageTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/


public class ReflectiveMethodInvocationTest extends AbstractV5Test{

    private AspectJBeforeAdvice beforeAdvice = null;
    private AspectJAfterReturningAdvice afterAdvice = null;
    private AspectJAfterThrowingAdvice afterThrowingAdvice;
    private AspectJExpressionPointcut pointcut;
    private PetStoreService petStoreService = null;
    private AspectInstanceFactory aspectInstanceFactory;
    private BeanFactory beanFactory;

    @Before
    public void setUp() throws Exception{
        petStoreService = new PetStoreService();
        pointcut = new AspectJExpressionPointcut();
        beanFactory = this.getBeanFactory("petstore-v5.xml");

        aspectInstanceFactory = getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);


        MessageTracker.clearMsg();
        beforeAdvice = new AspectJBeforeAdvice(getAdviceMethod("start"),pointcut,aspectInstanceFactory);
        afterAdvice = new AspectJAfterReturningAdvice(getAdviceMethod("commit"),pointcut,aspectInstanceFactory);
        afterThrowingAdvice = new AspectJAfterThrowingAdvice(getAdviceMethod("rollback"),pointcut,aspectInstanceFactory);

    }


    @Test
    public void testMethodInvocation() throws Throwable{
        Method targetMethod = PetStoreService.class.getMethod("placeOrder");

        List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
        interceptors.add(beforeAdvice);
        interceptors.add(afterAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(petStoreService,targetMethod,new Object[0],interceptors);
        mi.proceed();

        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3,msgs.size());
        Assert.assertEquals("transaction start1",msgs.get(0));
        Assert.assertEquals("place order",msgs.get(1));
        Assert.assertEquals("transaction commit1",msgs.get(2));


    }


    @Test
    public void testAfterThrowing() throws Throwable {
        Method targetMethod = PetStoreService.class.getMethod("placeOrderWithException");
        List<MethodInterceptor> interceptors = new ArrayList<MethodInterceptor>();
        interceptors.add(afterThrowingAdvice);
        interceptors.add(beforeAdvice);


        ReflectiveMethodInvocation im = new ReflectiveMethodInvocation(petStoreService,targetMethod,new Object[0],interceptors);
        try{
            im.proceed();
        }catch (Exception e){
            List<String> msgs = MessageTracker.getMsgs();
            Assert.assertEquals(2,msgs.size());
            Assert.assertEquals("transaction start1",msgs.get(0));
            Assert.assertEquals("transaction rallback1",msgs.get(1));
            return;
        }


        Assert.fail("No Exception throwed");





    }

}
