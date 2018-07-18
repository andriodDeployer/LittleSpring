package org.litespring.context.annotation;/**
 * Created by DELL on 2018/7/18.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResouceLoader;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.classreading.MetadataReader;
import org.litespring.core.type.classreading.SimpleMetadataReader;
import org.litespring.stereotype.Component;
import org.litespring.util.StringUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * user is
 **/


public class ClassPathDefinitionScanner {

    private final BeanDefinitionRegistry registry;//这个实例主要用来查询和存放beanDefinatione，具体从哪查以及存放到哪去，就要看这个registry的具体实现了
    private PackageResouceLoader resouceLoader = new PackageResouceLoader();
    protected final Log logger = LogFactory.getLog(getClass());

    public ClassPathDefinitionScanner(BeanDefinitionRegistry registry){
        this.registry = registry;
    }

    public Set<BeanDefinition> doScan(String packageToScan){
        String[] basePackages = StringUtils.tokenizeToStringArray(packageToScan,",");
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
        for(String basePackage : basePackages){
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for(BeanDefinition bd : candidates){
                beanDefinitions.add(bd);
            }
        }

        return beanDefinitions;
    }

    private Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();

        try {
            Resource[] resources = resouceLoader.getResouces(basePackage);
            for (Resource resource : resources) {
                MetadataReader metataReader = new SimpleMetadataReader(resource);
                AnnotationMetadata annotationMetadata = metataReader.getAnnotationMetadata();
                if (annotationMetadata.hasAnnotation(Component.class.getName())) {
                    ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(annotationMetadata);
                    candidates.add(sbd);
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return candidates;
    }


}
