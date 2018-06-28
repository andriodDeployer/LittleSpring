package org.litespring.beans;/**
 * Created by DELL on 2018/6/25.
 */

import org.litespring.beans.factory.BeanFactory;

/**
 * user is
 **/


public abstract class PropertyValue {

    private final String name;
    private final String value;

    private boolean converted = false;
    private Object convertedValue;

    public PropertyValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public abstract Object resoveValue(BeanFactory beanFactory);

    public String getValue() {
        return value;
    }

    public synchronized boolean isConverted() {
        return converted;
    }

    public Object getConvertedValue() {
        return convertedValue;
    }

    public void setConvertedValue(){
        this.converted = true;
        this.convertedValue = value;

    }
}
