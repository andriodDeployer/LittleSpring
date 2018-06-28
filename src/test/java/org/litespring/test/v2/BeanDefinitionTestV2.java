package org.litespring.test.v2;/**
 * Created by Administrator on 2018-6-24 0024.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.RuntimeBeanReferencePropertyValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;

import java.util.List;

/**
 * user is lwb
 **/


public class BeanDefinitionTestV2 {

    @Test
    public void testGetBeanDefinition(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        List<PropertyValue> pvs = bd.getPropertyValues();
        Assert.assertTrue(pvs.size() == 4);

        PropertyValue pv = getPropertyValue("accountDao",pvs);
        Assert.assertNotNull(pv);
        Assert.assertTrue(pv instanceof RuntimeBeanReferencePropertyValue);

        PropertyValue pv1 = getPropertyValue("itemDao",pvs);
        Assert.assertNotNull(pv1);
        Assert.assertTrue(pv1 instanceof RuntimeBeanReferencePropertyValue);


        PropertyValue pv2 = getPropertyValue("owner",pvs);
        Assert.assertNotNull(pv2);
        Assert.assertEquals("tom",pv2.resoveValue(factory));

    }

    private PropertyValue getPropertyValue(String propertyName,List<PropertyValue> pvs){
        for(PropertyValue pv : pvs){
            if(pv.getName().equals(propertyName)){
                return pv;
            }
        }
        return null;
    }
}
