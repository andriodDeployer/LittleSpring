package org.litespring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * Created by DELL on 2018/7/16.
 */

@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    boolean required() default true;
}
