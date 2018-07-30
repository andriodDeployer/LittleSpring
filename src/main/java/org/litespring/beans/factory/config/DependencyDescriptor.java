package org.litespring.beans.factory.config;
/**
 * Created by DELL on 2018/7/28.
 */


import org.litespring.util.Assert;

import java.lang.reflect.Field;

/**
 * user is
 **/

/**
 * 对依赖的信息进行封装（由于依赖的信息很多很复杂，将这些信息封装在一个类中，在解析的过程中，只需要面向这个类，封装了变化）
 * 在解析依赖的时候，只需要面向这一个类即可。
 */

public class DependencyDescriptor {

    private Field field;
    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        Assert.notNull(field,"Field must not be null");
        this.field = field;
        this.required = required;
    }

    public Class<?> getDependencyType(){
        if(this.field != null){
            return field.getType();
        }
        throw new RuntimeException("only support field dependency");
    }

    public boolean isRequired() {
        return this.required;
    }


}
