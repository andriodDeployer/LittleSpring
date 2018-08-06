package org.litespring.test.v5;/**
 * Created by DELL on 2018/8/6.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectJ.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectJ.AspectJAfterThrowingAdvice;
import org.litespring.aop.aspectJ.AspectJBeforeAdvice;
import org.litespring.aop.aspectJ.AspectJExpressionPointcut;
import org.litespring.aop.framework.AopConfig;
import org.litespring.aop.framework.AopConfigSupport;
import org.litespring.aop.framework.CglibProxyFactory;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;
import org.litespring.util.MessageTracker;

import java.util.List;

/**
 * user is lwb
 **/


public class CglibProxyTest {


    private static AspectJBeforeAdvice beforeAdvice = null;
    private static AspectJAfterReturningAdvice afterReturningAdvice = null;
    private static AspectJAfterThrowingAdvice throwingAdvice = null;
    private static AspectJExpressionPointcut pointcut = null;
    private PetStoreService petStoreService = null;
    private TransactionManager transactionManager;


    @Before
    public void setUp() throws Exception{
        petStoreService = new PetStoreService();
        transactionManager = new TransactionManager();
        String expression = "execution(* org.litespring.service.v5.*.placeOrder(..))";
        pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getMethod("start"),pointcut,transactionManager);
        afterReturningAdvice = new AspectJAfterReturningAdvice(TransactionManager.class.getMethod("commit"),pointcut,transactionManager);
    }


    @Test
    public void testGetProxy(){
        AopConfig aopConfig = new AopConfigSupport();

        aopConfig.addAdvice(beforeAdvice);
        aopConfig.addAdvice(afterReturningAdvice);
        aopConfig.setTargetObject(new PetStoreService());

        CglibProxyFactory proxyFactory = new CglibProxyFactory(aopConfig);
        PetStoreService proxy = (PetStoreService) proxyFactory.getProxy();
        proxy.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3,msgs.size());
        Assert.assertEquals("transaction start1",msgs.get(0));
        Assert.assertEquals("place order",msgs.get(3));
        Assert.assertEquals("transaction commit1",msgs.get(2));






    }
}
