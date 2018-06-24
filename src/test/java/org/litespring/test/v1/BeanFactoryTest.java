package org.litespring.test.v1;
/**
 * Created by DELL on 2018/6/19.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.service.v1.PetStoreService;

/**
 * user is lwb
 **/

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XMLBeanDefinitionReader reader = null;
    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();
        reader = new XMLBeanDefinitionReader(factory);
    }



    //1.测试用例，从指定xml中获取beanDefinition(bean定义)
    @Test
    public void testGetBean() {

        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));

        BeanDefinition bd = factory.getBeanDefinition("petStore");

        Assert.assertTrue(bd.isSingleton());
        Assert.assertFalse(bd.isPrototype());
        Assert.assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());

        Assert.assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());
        PetStoreService store = (PetStoreService) factory.getBean("petStore");
        Assert.assertNotNull(store);

        PetStoreService store1 = (PetStoreService) factory.getBean("petStore");
        Assert.assertTrue(store.equals(store1));
    }



    //2.测试用例，获取一个非法的bean。
    @Test
    public void testInvalidBean() {

        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));

        try{
            factory.getBean("invaildBean");
        }catch (BeanCreationException e){
            return;
        }

        Assert.fail("expect BeanCreationException");
    }

    //3.测试用例，测试一个不存在的资源(资源路径错误)
    @Test
    public void testInvalidXML(){
        try{
            reader.loadBeanDefinitions(new ClassPathResource("xxx.xml"));
        }catch (BeanDefinitionStoreException e){
            return;
        }
        Assert.fail("except BeanDefinitionStoreException");
    }
}
