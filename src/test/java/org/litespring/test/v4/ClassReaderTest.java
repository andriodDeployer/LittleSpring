package org.litespring.test.v4;
/**
 * Created by DELL on 2018/7/17.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.AnnotationMetadataReadingVisitor;
import org.litespring.core.type.classreading.ClassMetadataReadingVisitor;
import org.springframework.asm.ClassReader;

import java.io.IOException;

/**
 * user is lwb
 **/


public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        reader.accept(visitor,ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertFalse(visitor.isInterface());

        Assert.assertEquals("org.litespring.service.v4.PetStoreService",visitor.getClassName());
    }

    @Test
    public void testGetAnnotion() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        String annotationName = "org.litespring.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotationName));


        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotationName);

        Assert.assertEquals("petstore",attributes.getString("value"));


    }

}
