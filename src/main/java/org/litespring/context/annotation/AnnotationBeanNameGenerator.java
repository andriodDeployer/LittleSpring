package org.litespring.context.annotation;/**
 * Created by DELL on 2018/7/18.
 */

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.BeanNameGenerator;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.util.ClassUtils;
import org.litespring.util.StringUtils;

import java.beans.Introspector;
import java.util.Set;

/**
 * user is
 **/


public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if(definition instanceof AnnotatedBeanDefinition){
            String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition)definition);
            if(StringUtils.hasText(beanName)) {
                return beanName;
            }
        }
        return buildDefaultBeanName(definition,registry);
    }

    private String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return buildDefaultBeanName(definition);
    }

    //根据beanName，按照一定算法产生beanId。
    private String buildDefaultBeanName(BeanDefinition definition) {
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        return Introspector.decapitalize(shortClassName);
    }

    //从注解中找出一个value值，做为beanId
    private String determineBeanNameFromAnnotation(AnnotatedBeanDefinition definition) {
        AnnotationMetadata amd = definition.getMetadata();
        Set<String> types = amd.getAnnotationType();
        String beanName = null;
        for(String type : types){
            AnnotationAttributes attributes = amd.getAnnotationAttributes(type);
            Object value = attributes.get("value");
            if(value != null){
                if(value instanceof String){
                    String strVal = (String) value;
                    if(StringUtils.hasLength(strVal)){
                        beanName = strVal;
                        return beanName;
                    }
                }
            }
        }
        return beanName;
    }
}
