package org.litespring.aop.config;
/**
 * Created by DELL on 2018/7/31.
 */

import org.litespring.beans.BeanUtils;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.util.StringUtils;

import java.lang.reflect.Method;


/**
 * 定位一个方法就是获取到这个方法的对象，
 * 将定位一个方法需要的信息(方法名，方法所在的类)封装在一个类里面，将 根据这些信息获取到一个方法对象的算法 在类中实现。
 * 面向对象编程
 */

public class MethodLocatingFactory {

    //字段：使用来描述一个类的属性，很显然这里只需要三个字段既可以定位一个方法了，这个类中虽然用到了BeanFactory，但是仅仅使用到了这个beanFactory，这个beanFactory不需要做为类的一个字段/状态
    private String targetBeanName;
    private String methodName;
    private Method method;

    public void setTargetBeanName(String targetBeanName){
        this.targetBeanName = targetBeanName;
    }

    public void setMethodName(String methodName){
        this.methodName = methodName;
    }

    public Method getObject(){
        return this.method;
    }


    public void setBeanFactory(BeanFactory beanFactory){
        if(!StringUtils.hasText(this.targetBeanName)) {
            throw new IllegalArgumentException("Property 'targetBeanName' is required");
        }

        if(!StringUtils.hasText(this.methodName)){
            throw new IllegalArgumentException("Property 'methodName' is required");
        }

        Class<?> beanClass = beanFactory.getType(this.targetBeanName);
        if(beanClass == null){
            throw new IllegalArgumentException("Can't determine type of bean with name '" + this.targetBeanName);
        }

        this.method = BeanUtils.resolveSignature(this.methodName, beanClass);

        if (this.method == null) {
            throw new IllegalArgumentException("Unable to locate method [" + this.methodName +
                    "] on bean [" + this.targetBeanName + "]");
        }
    }
}
