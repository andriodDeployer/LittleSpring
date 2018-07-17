package org.litespring.core.type;/**
 * Created by DELL on 2018/7/17.
 */

import org.litespring.core.annotation.AnnotationAttributes;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.Map;

/**
 * user is
 **/


public class AnnotationAttributesReadingVisitor extends AnnotationVisitor {
    private String annotationType;
    private Map<String,AnnotationAttributes> attributeMap;
    AnnotationAttributes attributes = new AnnotationAttributes();

    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super(SpringAsmInfo.ASM_VERSION);
        this.annotationType = annotationType;
        this.attributeMap = attributesMap;
    }


    @Override
    public void visitEnd() {
        super.visitEnd();
        this.attributeMap.put(this.annotationType,this.attributes);
    }
}
