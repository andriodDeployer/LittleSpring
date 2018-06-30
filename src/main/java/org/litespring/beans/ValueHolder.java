package org.litespring.beans;/**
 * Created by Administrator on 2018-6-29 0029.
 */

/**
 * user is
 **/


public class ValueHolder {
    private Object value;
    private String type;
    private String name;

    public ValueHolder(Object value, String type) {
        this.value = value;
        this.type =type;
    }

    public ValueHolder(Object value){
        this.value = value;
    }

    public ValueHolder(Object value, String type, String name){
        this.value = value;
        this.type = type;
        this.name = name;
    }

    public Object getValue(){
        return value;
    }
}
