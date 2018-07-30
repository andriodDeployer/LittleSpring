package org.litespring.test.v4;/**
 * Created by DELL on 2018/7/18.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.context.annotation.ClassPathBeanDefinitionScanner;
import org.litespring.context.annotation.ScannedGenericBeanDefinition;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.stereotype.Component;

/**
 * user is lwb
 **/


public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void testParseScanedBean(){

        DefaultBeanFactory factory = new DefaultBeanFactory();
        String basePackage = "org.";

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackage);
        String annotation = Component.class.getName();

        {
            BeanDefinition bd = factory.getBeanDefinition("petStore");
            Assert.assertNotNull(bd);
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }

        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            Assert.assertNotNull(bd);
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }

        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            Assert.assertNotNull(bd);
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition) bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }

    }
}