package org.litespring.test.v5;/**
 * Created by DELL on 2018/8/11.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.aop.aspectJ.AspectJBeforeAdvice;
import org.litespring.aop.aspectJ.AspectJExpressionPointcut;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.tx.TransactionManager;

import java.util.List;

/**
 * user is lwb
 **/


public class BeanDefinationTestV5 extends AbstractV5Test{

    @Test
    public void AopBeanTest(){
        DefaultBeanFactory factory = (DefaultBeanFactory) this.getBeanFactory("petstore-v5.xml");

        //检查advice逻辑实现是否存在
        {
            BeanDefinition bd = factory.getBeanDefinition("tx");
            Assert.assertTrue(bd.getBeanClassName().equals(TransactionManager.class.getName()));
        }

        //检查pointcut是否存在,因为pointcut在xml中的定义规则并不满足，常规bean的定义，要让pointCut的信息存放到BeanDefination中需要进行一定的转换
        {
            //这个beandefination是合成的，因为定义中没有bean中没有property标签，而是直接使用expression进行表示property，要将expression默认当成property
            BeanDefinition bd = factory.getBeanDefinition("placeOrder");

            Assert.assertTrue(bd.isSynthtic());
            Assert.assertTrue(bd.getBeanClass().equals(AspectJExpressionPointcut.class));
            PropertyValue pv = bd.getPropertyValues().get(0);
            Assert.assertEquals("expression",pv.getName());
            Assert.assertEquals("execution(* org.litespring.service.v5.*.placeOrder(..))",pv.getValue());

        }

        //检查AspectJBeforeAdvice
        {
            //将定义在xml文件中的beforeAdvice，用BeanDefination表示是，因为advice的定义也不满足常规的bean定义，如果要让beandefination表示的话，需要进行一定的合成。
            //也就是按照一定对应方式，存放到beandefination中，同时由于一中类型的advice可能绑定到多个pointcut中，那么生成多个advice要有不同的id才可以。
            String name = AspectJBeforeAdvice.class.getName() +"#0";
            BeanDefinition bd = factory.getBeanDefinition(name);
            Assert.assertTrue(bd.getBeanClass().equals(AspectJBeforeAdvice.class));

            Assert.assertTrue(bd.isSynthtic());
            List<PropertyValue> propertyValues = bd.getPropertyValues();
            Assert.assertEquals(1,propertyValues.size());



        }


    }




}
