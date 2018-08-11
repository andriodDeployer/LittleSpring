package org.litespring.test.v5;/**
 * Created by DELL on 2018/8/11.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.aop.Advice;
import org.litespring.aop.aspectJ.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectJ.AspectJAfterThrowingAdvice;
import org.litespring.aop.aspectJ.AspectJBeforeAdvice;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.tx.TransactionManager;

import java.util.List;

/**
 * user is lwb
 **/


public class BeanFactoryTest extends AbstractV5Test{

    static String expectedExpression = "execution(* org.litespring.service.v5.*.placeOrder(..))";
    @Test
    public void testGetBean() throws Exception{
        BeanFactory factory = super.getBeanFactory("petstore-v5.xml");
        List<Object> advices = factory.getBeansByType(Advice.class);
        Assert.assertEquals(3,advices.size());

        {
            AspectJBeforeAdvice advice = (AspectJBeforeAdvice) this.getAdvice(AspectJBeforeAdvice.class, advices);
            Assert.assertEquals(TransactionManager.class.getMethod("start"),advice.getAdviceMethod());
            Assert.assertEquals(expectedExpression,advice.getPointcut().getExpression());
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
        }

        {
            AspectJAfterReturningAdvice advice = (AspectJAfterReturningAdvice) this.getAdvice(AspectJAfterReturningAdvice.class, advices);
            Assert.assertEquals(TransactionManager.class.getMethod("commit"),advice.getAdviceMethod());
            Assert.assertEquals(expectedExpression,advice.getPointcut().getExpression());
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
        }

        {
            AspectJAfterThrowingAdvice advice = (AspectJAfterThrowingAdvice) this.getAdvice(AspectJAfterThrowingAdvice.class, advices);
            Assert.assertEquals(TransactionManager.class.getMethod("rollback"),advice.getAdviceMethod());
            Assert.assertEquals(expectedExpression,advice.getPointcut().getExpression());
            Assert.assertEquals(TransactionManager.class,advice.getAdviceInstance().getClass());
        }
    }

    private Object getAdvice(Class<?> type, List<Object> advices) {
        for(Object advice : advices){
            if(advice.getClass().equals(type)){
                return advice;
            }
        }
        return null;
    }
}
