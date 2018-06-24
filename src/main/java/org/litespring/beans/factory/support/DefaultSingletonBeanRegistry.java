package org.litespring.beans.factory.support;
/**
 * Created by DELL on 2018/6/21.
 */

import org.litespring.beans.factory.config.SingletonBeanRegistry;
import org.litespring.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * user is lwb
 **/


public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletons = new HashMap<String,Object>();

    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName,"'beanName' must not be null ");
        Object oldObject = singletons.get(beanName);
        if(oldObject != null){
            throw new IllegalStateException("Could not register object ["+ singletonObject +"] under bean name '"+beanName+"': there is already object ["+oldObject+"]");
        }
        singletons.put(beanName,singletonObject);
    }

    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }
}
