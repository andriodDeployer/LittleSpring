package org.litespring.test.v4;/**
 * Created by DELL on 2018/7/17.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.classreading.MetadataReader;
import org.litespring.core.type.classreading.SimpleMetadataReader;
import org.litespring.stereotype.Component;

import java.io.IOException;

/**
 * user is lwb
 **/


public class MetadataReaderTest {

    @Test
    public void testGetMetadata() throws IOException{
        ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
        MetadataReader reader = new SimpleMetadataReader(resource);

        AnnotationMetadata amd = reader.getAnnotationMetadata();
        String annotation = Component.class.getName();

        Assert.assertTrue(amd.hasAnnotation(annotation));
        Assert.assertEquals("org.litespring.service.v4.PetStoreService",amd.getClassName());
        Assert.assertEquals("petstore",amd.getAnnotationAttributes(annotation).getString("value"));
    }

}
