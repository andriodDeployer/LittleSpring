package org.litespring.beans.factory;/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.beans.BeansException;

/**
 * user is lwb
 **/

/**
 * 创建bean实例是产生的异常，因为bean是由beanfactory进行创建的，所以放在factory包下
 */
public class BeanCreationException extends BeansException {
    private String beanName;

    public BeanCreationException(String msg) {
        super(msg);
    }

    public BeanCreationException(String msg, Throwable cause){
        super(msg, cause);
    }

    public BeanCreationException(String beanName, String msg){
        super("Error creating bean with name '" + beanName + "':" + msg);
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName, String msg, Throwable cause) {
        this(beanName,msg);
        initCause(cause);
    }

    public String getBeanName(){
        return beanName;
    }

}
