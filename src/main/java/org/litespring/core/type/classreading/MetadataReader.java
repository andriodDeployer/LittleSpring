package org.litespring.core.type.classreading;

import org.litespring.core.io.Resource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.ClassMetadata;

/**
 * Created by DELL on 2018/7/18.
 */
public interface MetadataReader {

    //获取指定class文件的resouce
    Resource getResource();

    ClassMetadata getClassMetadata();//class的metadata

    AnnotationMetadata getAnnotationMetadata();//annotation的metadata

}
