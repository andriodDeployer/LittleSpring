package org.litespring.beans.factory.config;/**
 * Created by DELL on 2018/6/25.
 */

/**
 * user is
 **/


public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName){
        this.beanName = beanName;
    }

    public String getBeanName(){
        return beanName;
    }


}
