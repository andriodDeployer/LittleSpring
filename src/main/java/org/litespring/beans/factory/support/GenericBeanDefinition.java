package org.litespring.beans.factory.support;
/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.beans.BeanDefinition;

/**
 * user is
 **/


public class GenericBeanDefinition implements BeanDefinition {

    private String beanId;
    private String beanClassName;
    private String scope;
    private boolean singleton = true;
    private boolean prototype = false;

    public GenericBeanDefinition(String beanId, String beanClassName) {
        this.beanId = beanId;
        this.beanClassName = beanClassName;
    }

    public String getBeanClassName() {
        return beanClassName;
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
}
