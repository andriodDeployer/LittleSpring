package org.litespring.core.type;

import org.litespring.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * Created by DELL on 2018/7/18.
 */
public interface AnnotationMetadata extends ClassMetadata{

    Set<String> getAnnotationType();//属于class的元数据信息。使用ClassVisitor

    boolean hasAnnotation(String annotationType);

    AnnotationAttributes getAnnotationAttributes(String annotationType);//属于annotation的元数据。要获取Annotation的元数据的话，需要AnnotationVisitor

}
