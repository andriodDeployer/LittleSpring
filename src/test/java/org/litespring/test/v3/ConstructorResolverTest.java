package org.litespring.test.v3;/**
 * Created by Administrator on 2018-6-30 0030.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.ConstructorResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v3.PetStoreService;

/**
 * user is lwb
 **/


public class ConstructorResolverTest {

    @Test
    public void testAutowireConstructor(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v3.xml");
        reader.loadBeanDefinitions(resource);

        BeanDefinition bd = factory.getBeanDefinition("petStore");
        ConstructorResolver resolver = new ConstructorResolver(factory);
        PetStoreService petStoreService = (PetStoreService) resolver.autowireConstructor(bd);


        Assert.assertEquals(1,petStoreService.getVersion());
    }
}
