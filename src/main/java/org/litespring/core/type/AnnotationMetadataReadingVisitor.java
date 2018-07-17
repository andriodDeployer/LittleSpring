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

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className,attributesMap);
    }
}
