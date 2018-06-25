package org.litespring.beans;/**
 * Created by DELL on 2018/6/25.
 */

/**
 * user is
 **/


public class PropertyValue {

    private final String name;
    private final Object value;

    private boolean converted = false;
    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
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
