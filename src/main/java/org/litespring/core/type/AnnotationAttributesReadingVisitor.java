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

    //当一个annotation被解析完之后，调用一次visitEnd，那个这个visitor也就是没有用了，一个annotation对应一个annotationVisitor对象
    @Override
    public void visitEnd() {
        super.visitEnd();
        this.attributeMap.put(this.annotationType,this.attributes);
    }

    //每解析一个annotatio的attribute，都会调用一次visit方法。
    @Override
    public void visit(String attributeName, Object attributeValue) {
        this.attributes.put(attributeName,attributeValue);
    }
}
