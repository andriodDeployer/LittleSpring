package org.litespring.core.type;/**
 * Created by DELL on 2018/7/17.
 */

import jdk.internal.org.objectweb.asm.Type;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.springframework.asm.AnnotationVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * user is
 **/


public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor {
    private final Set<String> annotationSet = new LinkedHashSet<String>(4);
    private final Map<String, AnnotationAttributes> attributesMap = new LinkedHashMap<String,AnnotationAttributes>(4);



    public AnnotationMetadataReadingVisitor() {super();}

    //如果解析的类中(不是对象)有注解的话，有一个注解调用一次这个方法。classReader拿到这个方法的返回值(一个AnnotationVisitor)后，
    // 在解析annotiation的过程中，在调用annotationVisitor的相关方法,例如在解析annotation时，对于每个attribute会调用一次annotationVisitor的visit方法。
    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className,attributesMap);
    }

    public AnnotationAttributes getAnnotationAttributes(String annotationName){
        return attributesMap.get(annotationName);
    }

    public boolean hasAnnotation(String annotationName){
        return annotationSet.contains(annotationName);
    }
}
