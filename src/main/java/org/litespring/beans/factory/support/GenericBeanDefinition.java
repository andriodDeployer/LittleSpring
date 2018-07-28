package org.litespring.beans.factory.support;
/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * user is
 **/


public class GenericBeanDefinition implements BeanDefinition {

    private String beanId;
    private String beanClassName;
    private Class beanClass;
    private String scope;
    private boolean singleton = true;
    private boolean prototype = false;
    private List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
    private ConstructorArgument constructorArgument = new ConstructorArgument();

    public  GenericBeanDefinition(String beanId, String beanClassName) {
        this.beanId = beanId;
        this.beanClassName = beanClassName;
    }

    public GenericBeanDefinition() {
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public boolean hasBeanClass() {
        return beanClass == null;
    }

    public Class<?> getBeanClass() {
        if(this.beanClass == null){
            //抛出这个异常说明，getBeanClass这个方法，一定能够返回class，就是为了较少调用getBeanClass方法的不确定性(再次进行非空判断)，如果在调用getBeanClass时，如果不确定是否有值，就先调用resolveBeanClass方法
            throw new IllegalStateException("Bean class name [" + this.getBeanClassName()+"] has not bean resolved into");
        }

        return beanClass;
    }

    public Class<?> resoveBeanClass(ClassLoader classLoader) throws ClassNotFoundException{
        String className = getBeanClassName();
        if(className == null){
            return null;
        }
        Class<?> resolveClass = classLoader.loadClass(className);
        this.beanClass = resolveClass;
        return resolveClass;
    }

    public void setBeanClassName(String beanClassName){
        this.beanClassName = beanClassName;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public ConstructorArgument getConstructorArgument() {
        return constructorArgument;
    }

    public String getID() {
        return beanId;
    }

    public void setID(String beanId){
        this.beanId = beanId;
    }

    public boolean hasConstructorArgumentValues() {
        return !getConstructorArgument().isEmpty();
    }

}
