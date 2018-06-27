package org.litespring.beans.propertyeditors;/**
 * Created by DELL on 2018/6/26.
 */

import org.litespring.util.NumberUtils;
import org.litespring.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * user is
 **/

//将string类型的数据转化成number类型数据，具体转换成何种number(number包含integer,double,float)，由targetClass指定。
public class CustomNumberEditor extends PropertyEditorSupport{
    private final Class<? extends Number> numberClass;
    private final NumberFormat numberFormat;
    private final boolean allowEmpty;
    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) {
        this(numberClass,null,allowEmpty);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) {
        if(numberClass == null || !Number.class.isAssignableFrom(numberClass)){
            throw new IllegalArgumentException("Property class must be a subclass if Number");
        }
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }


    @Override
    public void setValue(Object value) {
        if(value instanceof Number){
            super.setValue(NumberUtils.convertNumberToTargetClass((Number) value,this.numberClass));
        }else{
            super.setValue(value);
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
       if(this.allowEmpty && !StringUtils.hasText(text)) {
           setValue(null);
       }else if(this.numberFormat != null){
           setValue(NumberUtils.parseNumber(text,this.numberClass,this.numberFormat));
       }else{
           setValue(NumberUtils.parseNumber(text,this.numberClass));
       }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        if(value == null){
            return "";
        }
        if(numberFormat != null){
            return numberFormat.format(value);
        }else{
            return value.toString();
        }
    }
}
