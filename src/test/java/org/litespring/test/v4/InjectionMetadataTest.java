package org.litespring.test.v4;/**
 * Created by DELL on 2018/7/28.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.annotation.AutowiredFieldElement;
import org.litespring.beans.factory.annotation.InjectionElement;
import org.litespring.beans.factory.annotation.InjectionMetadata;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.v4.AccountDao;
import org.litespring.dao.v4.ItemDao;
import org.litespring.service.v4.PetStoreService;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * user is lwb
 **/


public class InjectionMetadataTest {

    /**
     * InjectionElement是DependencyDescriptor的更高层次的一种封装
     * 一个类的所有的injection放在一个列表中，这个列表封装在一个InjectionMetaData中
     * 在InjectionMetaData中存在一个方法inject，完成对指定实例中的所以依赖(injection列表)进行注入，
     * 就是利用factory.resolveDependency(DependencyDescriptor)找到依赖类型的实例，然后通过setter存放进入
     */

    @Test
    public void testInjection() throws Exception{
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinitions(resource);

        Class<?> clz = PetStoreService.class;
        //一个类需要注入的依赖全部都放在一个列表中
        LinkedList<InjectionElement> injectioinElements = new LinkedList<InjectionElement>();

        {
            Field f = PetStoreService.class.getDeclaredField("accountDao");
            InjectionElement injectionElement = new AutowiredFieldElement(f,true,factory);
            injectioinElements.add(injectionElement);
        }
        {
            Field f = PetStoreService.class.getDeclaredField("itemDao");
            InjectionElement injectionElement = new AutowiredFieldElement(f,true,factory);
            injectioinElements.add(injectionElement);
        }
        InjectionMetadata metadata = new InjectionMetadata(clz,injectioinElements);

        PetStoreService petStoreService = new PetStoreService();
        metadata.inject(petStoreService);

        Assert.assertTrue(petStoreService.getAccountDao() instanceof AccountDao);
        Assert.assertTrue(petStoreService.getItemDao() instanceof ItemDao);

    }
}
