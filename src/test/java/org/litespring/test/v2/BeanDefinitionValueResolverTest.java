package org.litespring.test.v2;
/**
 * Created by DELL on 2018/6/26.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionValueResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.dao.v2.AccountDao;

/**
 * user is lwb
 **/


public class BeanDefinitionValueResolverTest {
    BeanDefinitionValueResolver resolver = null;
    @Before
    public void setUp(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        new XMLBeanDefinitionReader(factory).loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
        resolver = new BeanDefinitionValueResolver(factory);
    }


    //测试BeanDefinitionValueResolver的功能：resolveValueIfNecessary方法，将RuntimeBeanReference,转换成一个bean。
    @Test
    public void testResolveRuntimeBeanReference(){
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(reference);
        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);

    }


    @Test
    public void testResolveTypedStringValue(){
        TypedStringValue typedStringValue = new TypedStringValue("test");
        Object value = resolver.resolveValueIfNecessary(typedStringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test",value);
    }
}
