package org.litespring.beans.factory.annotation;/**
 * Created by DELL on 2018/7/28.
 */

import org.litespring.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Member;

/**
 * user is
 **/

/**
 * InjectionElement是对DependencyDescriptor的一种封装，
 * 变化抽象，将需要的注入的类/方法/字段的注入，进行抽象，具体针对不同的部分的注入，进行不同的实现
 */
public abstract class InjectionElement {

    protected Member member;
    protected AutowireCapableBeanFactory factory;

    InjectionElement(Member member,AutowireCapableBeanFactory factory){
        this.member = member;
        this.factory = factory;
    }
    public abstract void inject(Object target);
}
