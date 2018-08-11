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
import org.litespring.aop.config.AspectInstanceFactory;
import org.litespring.aop.framework.AopConfig;
import org.litespring.aop.framework.AopConfigSupport;
import org.litespring.aop.framework.CglibProxyFactory;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.service.v5.PetStoreService;
import org.litespring.util.MessageTracker;

import java.util.List;

/**
 * user is lwb
 **/


public class CglibProxyFactoryTest extends AbstractV5Test{


    private static AspectJBeforeAdvice beforeAdvice = null;
    private static AspectJAfterReturningAdvice afterReturningAdvice = null;
    private static AspectJAfterThrowingAdvice throwingAdvice = null;
    private static AspectJExpressionPointcut pointcut = null;
    private BeanFactory beanFactory;
    private AspectInstanceFactory aspectInstanceFactory;



    @Before
    public void setUp() throws Exception{
        MessageTracker.clearMsg();
        String expression = "execution(* org.litespring.service.v5.*.placeOrder(..))";
        pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        beanFactory = this.getBeanFactory("petstore-v5.xml");

        aspectInstanceFactory = getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);


        beforeAdvice = new AspectJBeforeAdvice(getAdviceMethod("start"),pointcut,aspectInstanceFactory);
        afterReturningAdvice = new AspectJAfterReturningAdvice(getAdviceMethod("commit"),pointcut,aspectInstanceFactory);
        throwingAdvice = new AspectJAfterThrowingAdvice(getAdviceMethod("rollback"),pointcut,aspectInstanceFactory);
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
        Assert.assertEquals("place order",msgs.get(1));
        Assert.assertEquals("transaction commit1",msgs.get(2));

        System.out.println(proxy.toString());

    }
}
