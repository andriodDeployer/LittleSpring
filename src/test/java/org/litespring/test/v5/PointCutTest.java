package org.litespring.test.v5;/**
 * Created by DELL on 2018/7/31.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.aop.MethodMatcher;
import org.litespring.aop.aspectJ.AspectJExpressionPointcut;
import org.litespring.service.v5.PetStoreService;

import java.lang.reflect.Method;

/**
 * user is lwb
 **/


public class PointCutTest {

    @Test
    public void testPointCut() throws Exception{
        String expression = "execution (* org.litespring.service.v5.*.placeOrder(..))";

        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);//生成一个matcher

        MethodMatcher mm = pc.getMethodMatcher();

        //下面开始验证上面关于pointcut的实现是否准确。
        //1.测试方法名匹配
        {
            Class<?> targetClass = PetStoreService.class;

            Method method1 = targetClass.getMethod("placeOder");
            Assert.assertTrue(mm.matches(method1));

            Method method2 = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method2));
        }

        //2.测试包名匹配
        {
            Class<?> targetClass = org.litespring.service.v4.PetStoreService.class;
            Method method = targetClass.getMethod("placeOrder");
            Assert.assertFalse(mm.matches(method));

        }


    }
}
