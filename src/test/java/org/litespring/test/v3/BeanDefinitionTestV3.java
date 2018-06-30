package org.litespring.test.v3;/**
 * Created by Administrator on 2018-6-24 0024.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.ValueHolder;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

import java.util.List;

/**
 * user is lwb
 **/

//测试constructor参数是否存放到BD中
public class BeanDefinitionTestV3 {

    @Test
    public void testGetBeanDefinition(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");
        Assert.assertEquals("org.litespring.service.v3.PetStoreService",bd.getBeanClassName());


        ConstructorArgument args = bd.getConstructorArgument();
        List<ValueHolder> valueHolders = args.getArgumentValues();
        Assert.assertTrue(valueHolders.size() == 3);

        //argument是有顺序的
        RuntimeBeanReference reference1 = (RuntimeBeanReference) valueHolders.get(0).getValue();
        Assert.assertEquals("accountDao",reference1.getBeanName());


    }


}
