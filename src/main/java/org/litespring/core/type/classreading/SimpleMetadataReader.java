package org.litespring.core.type.classreading;/**
 * Created by DELL on 2018/7/18.
 */

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.AnnotationMetadataReadingVisitor;
import org.litespring.core.type.ClassMetadata;
import org.springframework.asm.ClassReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * user is
 **/


public class SimpleMetadataReader implements MetadataReader {
    private Resource resource;
    private ClassMetadata classMetadata;
    private AnnotationMetadata annotationMetadata;

    public SimpleMetadataReader(ClassPathResource resource) throws IOException {
        InputStream is =  new BufferedInputStream(resource.getInputStream());
        ClassReader clasReader;

        try {
            clasReader = new ClassReader(is);
        }
        finally {
            is.close();
        }

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        clasReader.accept(visitor,ClassReader.SKIP_DEBUG);

        this.annotationMetadata = visitor;
        this.classMetadata = visitor;
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public ClassMetadata getClassMetadata() {
        return classMetadata;
    }

    public AnnotationMetadata getAnnotationMetadata() {
        return annotationMetadata;
    }
}
