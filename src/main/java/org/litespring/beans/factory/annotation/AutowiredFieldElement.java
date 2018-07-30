package org.litespring.beans.factory.annotation;/**
 * Created by DELL on 2018/7/30.
 */

import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.AutowireCapableBeanFactory;
import org.litespring.beans.factory.config.DependencyDescriptor;
import org.litespring.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * user is
 **/


public class AutowiredFieldElement extends InjectionElement {
    private boolean required ;

    public AutowiredFieldElement(Field field, boolean required, AutowireCapableBeanFactory factory) {
        super(field,factory);
        this.required = required;
    }

    public Field getField(){
        return (Field) this.member;
    }

    public void inject(Object target) {
        Field field = this.getField();
        try{
            DependencyDescriptor descriptor = new DependencyDescriptor(field,this.required);
            Object value = factory.resolveDependency(descriptor);
            if(value != null){
                ReflectionUtils.makeAccessible(field);
                field.set(target,value);
            }
        }catch (Throwable ex){
            throw new BeanCreationException("Could not autowire field: " + field, ex);
        }
    }
}
