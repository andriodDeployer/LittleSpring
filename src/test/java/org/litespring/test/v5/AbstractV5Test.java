package org.litespring.test.v5;/**
 * Created by DELL on 2018/8/11.
 */

import org.litespring.aop.config.AspectInstanceFactory;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.tx.TransactionManager;

import java.lang.reflect.Method;

/**
 * user is lwb
 **/


public class AbstractV5Test {


    protected BeanFactory getBeanFactory(String configFile){
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource(configFile);
        reader.loadBeanDefinitions(resource);
        return defaultBeanFactory;
    }

    protected Method getAdviceMethod(String methodName) throws Exception{
        return TransactionManager.class.getMethod(methodName);
    }


    protected AspectInstanceFactory getAspectInstanceFactory(String targetBeanName){
        AspectInstanceFactory factory = new AspectInstanceFactory();
        factory.setAspetcBeanName(targetBeanName);
        return factory;
    }
}
