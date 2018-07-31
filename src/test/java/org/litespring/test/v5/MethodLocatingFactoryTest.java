package org.litespring.test.v5;
/**
 * Created by DELL on 2018/7/31.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.aop.config.MethodLocatingFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.tx.TransactionManager;

import java.lang.reflect.Method;

/**
 * user is lwb
 **/


public class MethodLocatingFactoryTest {

    @Test
    public void methodLocatingTest() throws NoSuchMethodException {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v5.xml");
        reader.loadBeanDefinitions(resource);


        MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
        methodLocatingFactory.setTargetBeanName("tx");
        methodLocatingFactory.setMethodName("start");
        methodLocatingFactory.setBeanFactory(factory);

        Method method = methodLocatingFactory.getObject();

        //测试项目：
        //1.TransactionManager是否正确
        Assert.assertTrue(TransactionManager.class.equals(method.getDeclaringClass()));
        //2.获取到方法是否时TransactionManager的方法
        Assert.assertTrue(method.equals(TransactionManager.class.getMethod("start")));




    }


}
