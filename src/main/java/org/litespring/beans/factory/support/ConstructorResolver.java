package org.litespring.beans.factory.support;/**
 * Created by Administrator on 2018-6-30 0030.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.beans.*;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * user is
 **/


public class ConstructorResolver {
    protected final Log logger = LogFactory.getLog(getClass());
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

        ConstructorArgument constructorArgument
                = bd.getConstructorArgument();
        SimpleTypeConverter converter = new SimpleTypeConverter();
        for(int i =0;i<candicates.length;i++){
            Class<?> [] parameterTypes = candicates[i].getParameterTypes();
            if(parameterTypes.length != constructorArgument.getArgumentCount()){
                continue;
            }

            argToUse = new Object[parameterTypes.length];
            boolean result = valueMatchTypes(parameterTypes,constructorArgument.getArgumentValues(),argToUse,valueResolver,converter);
            if(result){
                constructorToUse = candicates[i];
                break;
            }
        }

        if(constructorArgument == null){
            throw new BeanCreationException(bd.getID(),"can't find a apporiate constructor");
        }

        try {
            return constructorToUse.newInstance(argToUse);
        }catch (Exception e){
            throw new BeanCreationException(bd.getID(),"can't find a create instance using "+constructorToUse);
        }
    }

    private boolean valueMatchTypes(Class<?>[] parameterTypes, List<ValueHolder> valueHolders,
                                    Object[] argsToUse,BeanDefinitionValueResolver valueResolver,
                                    SimpleTypeConverter converter){
        for(int i=0;i<parameterTypes.length;i++){
            ValueHolder valueHolder
                    = valueHolders.get(i);
            Object origionValue = valueHolder.getValue();//runtimeBeanDefinition/TypeStringValue;

            Object resolvedValue = valueResolver.resolveValueIfNecessary(origionValue);
            Object convertedValue = null;
            try {
                convertedValue = converter.convertIfNecessary(resolvedValue,parameterTypes[i]);
            } catch (TypeMismatchException e) {
                logger.error(e);
                return false;
            }
            argsToUse[i] = convertedValue;
        }
        return true;
    }
}
