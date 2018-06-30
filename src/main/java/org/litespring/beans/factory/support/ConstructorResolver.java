package org.litespring.beans.factory.support;/**
 * Created by Administrator on 2018-6-30 0030.
 */

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;

import java.lang.reflect.Constructor;

/**
 * user is
 **/


public class ConstructorResolver {
    private ConfigurableBeanFactory factory;

    public ConstructorResolver(ConfigurableBeanFactory factory) {
        this.factory = factory;
    }

    public Object autowireConstructor(BeanDefinition bd) {
        Constructor<?> constructorToUse = null;
        Object[] argToUse = null;
        Class<?> beanClass = null;

        try{
            beanClass = factory.getBeanClassLoader().loadClass(bd.getBeanClassName());
        }catch (ClassNotFoundException e){
            throw new BeanCreationException(bd.getID(),"");
        }

        Constructor<?>[] candicates = beanClass.getConstructors();
        BeanDefinitionValueResolver valueResolver =
                new BeanDefinitionValueResolver(factory);




        return null;
    }
}
