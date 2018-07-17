package org.litespring.core.annotation;/**
 * Created by DELL on 2018/7/17.
 */

import org.litespring.util.Assert;

import java.util.LinkedHashMap;

import static java.lang.String.format;

/**
 * user is
 **/

/**
 * 一个注解的所有字段与对应的值，采用Map数据结构进行存放
 */
public class AnnotationAttributes extends LinkedHashMap<String,Object>{

    public AnnotationAttributes(){

    }

    public AnnotationAttributes(int initialCapacity){
        super(initialCapacity);
    }

    public String getString(String attributeName){
        return doGet(attributeName,String.class);
    }

    public String[] getStringArray(String attributeName) {
        return doGet(attributeName, String[].class);
    }

    public boolean getBoolean(String attributeName) {
        return doGet(attributeName, Boolean.class);
    }

    @SuppressWarnings("unchecked")
    public <N extends Number> N getNumber(String attributeName) {
        return (N) doGet(attributeName, Integer.class);
    }

    @SuppressWarnings("unchecked")
    public <E extends Enum<?>> E getEnum(String attributeName) {
        return (E) doGet(attributeName, Enum.class);
    }

    @SuppressWarnings("unchecked")
    public <T> Class<? extends T> getClass(String attributeName) {
        return doGet(attributeName, Class.class);
    }

    public Class<?>[] getClassArray(String attributeName) {
        return doGet(attributeName, Class[].class);
    }




    private <T> T doGet(String attributeName,Class<T> exceptClass){
        Object value = this.get(attributeName);
        //使用format可以减少字符串的拼接
        Assert.notNull(value, format("Attribute '%s' not found",attributeName));
        return (T) value;
    }



}
