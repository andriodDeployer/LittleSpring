package org.litespring.beans;

/**
 * Created by DELL on 2018/6/27.
 */
public interface TypeConverter {

    <T> T convertIfNecessary(Object value,Class<T> requiredType) throws TypeMismatchException;
}
