package org.litespring.test.v4;/**
 * Created by DELL on 2018/7/27.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.config.DependencyDescriptor;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.v2.AccountDao;
import org.litespring.service.v4.PetStoreService;

import java.lang.reflect.Field;

/**
 * user is lwb
 **/


public class DependencyDescriptorTest {

    /**
     * 测试DependencyDescriptor，主要用来描述 方法的入参(类似于@RequestBody等)/字段 上注解的情况，也就是这个类对其他类型(字段/方法入参的其他类型)的依赖。
     * resolveDependency就是根据descriptor的猫叔生成依赖对象的实例。
     * @throws Exception
     */

    @Test
    public void testResolveDependency() throws Exception{

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinitions(resource);

        Field f = PetStoreService.class.getDeclaredField("accountDao");
        DependencyDescriptor descriptor = new DependencyDescriptor(f,true);
        Object o = factory.resolveDependency(descriptor);
        Assert.assertTrue(o instanceof AccountDao);



    }
}
