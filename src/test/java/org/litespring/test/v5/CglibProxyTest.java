package org.litespring.test.v5;/**
 * Created by DELL on 2018/8/6.
 */

import org.junit.Before;
import org.litespring.aop.aspectJ.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectJ.AspectJAfterThrowingAdvice;
import org.litespring.aop.aspectJ.AspectJBeforeAdvice;
import org.litespring.aop.aspectJ.AspectJExpressionPointcut;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;

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


    }
}
