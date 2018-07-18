package org.litespring.context.annotation;
/**
 * Created by DELL on 2018/7/18.
 */

import org.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.type.AnnotationMetadata;

/**
 * user is
 **/


public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {
    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata metadata){
        this.metadata = metadata;
        setBeanClassName(metadata.getClassName());
        //String id = metadata.getAnnotationAttributes(Component.class.getName()).getString("value");
        //setID(id);

    }

    public AnnotationMetadata getMetadata() {
        return metadata;
    }
}
