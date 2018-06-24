package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

/**
 * user is lwb
 **/

/**
 * 解析，存储beandefinition过程中产生的异常，因为这个beandefinition的解析和存储有beanfactory进行完成，所以这个类放在factory包中
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
