package org.litespring.core.type;/**
 * Created by DELL on 2018/7/17.
 */

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.SpringAsmInfo;

/**
 * user is
 **/


public class AnnotationMetadataReadingVisitor extends ClassVisitor {
    public AnnotationMetadataReadingVisitor() {
        super(SpringAsmInfo.ASM_VERSION);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        return super.visitAnnotation(s, b);
    }
}
