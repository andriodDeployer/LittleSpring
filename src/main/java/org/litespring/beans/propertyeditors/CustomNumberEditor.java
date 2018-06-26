package org.litespring.beans.propertyeditors;/**
 * Created by DELL on 2018/6/26.
 */

import org.litespring.util.StringUtils;

import java.text.NumberFormat;

/**
 * user is
 **/


public class CustomNumberEditor {
    private final Class<? extends Number> numberClass;
    private final NumberFormat numberFormat;
    private final boolean allowEmpty;
    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) {
        this(numberClass,null,allowEmpty);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) {
        if(numberClass == null || Number.class.isAssignableFrom(numberClass)){
            throw new IllegalArgumentException("Property class must be a subclass if Number");
        }
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    public void setAsText(String text) {
        if(this.allowEmpty && !StringUtils.hasText(text)){
            setValue(null);
        }else if(this.numberFormat != null){
            //setValue(NumberU);
        }
    }

    public Object getValue() {
        return null;
    }
    public void setValue(Object value){
        if(value instanceof Number){

        }

    }
}
