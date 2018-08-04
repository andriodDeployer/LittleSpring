package org.litespring.test.v5;/**
 * Created by DELL on 2018/8/4.
 */

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;
import org.litespring.util.MessageTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/


public class ReflectiveMethodInvocationTest {

    private AspectJBeforeAdvice beforeAdvice = null;
    private AspectJAfterReturningAdvice afterAdvice = null;
    private PetStoreService petStoreService = null;
    private TransactionManager tx;


    @Before
    public void setUp() throws Exception{
        petStoreService = new PetStoreService();
        tx = new TransactionManager();
        MessageTracker.clearMsg();
        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getMethod("start"),null,tx);

        afterAdvice = new AspectJAfterReturningAdvice(TransactionManager.class.getMethod("commit"),null,tx);
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
        Assert.assertEquals("transaction start",msgs.get(0));
        Assert.assertEquals("place order",msgs.get(1));
        Assert.assertEquals("transaction commit",msgs.get(2));


    }



}
