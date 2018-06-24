package org.litespring.service.v2;/**
 * Created by Administrator on 2018-6-24 0024.
 */

import org.junit.Test;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;

/**
 * user is lwb
 **/


public class BeanDefinitionTestV2 {

    @Test
    public void testGetBeanDefinition(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        //reader.loadBeanDefinitions("petstore-v2.xml");
       // BeanD
    }


}
