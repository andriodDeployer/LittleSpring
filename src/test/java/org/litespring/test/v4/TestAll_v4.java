package org.litespring.test.v4;/**
 * Created by DELL on 2018/7/30.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * user is lwb
 **/

@RunWith(Suite.class)
@Suite.SuiteClasses({
       // ApplicationContextTest4.class,
        AutowiredAnnotationProcessorTest.class,
        ClassPathBeanDefinitionScannerTest.class,
        ClassReaderTest.class,
        DependencyDescriptorTest.class,
        InjectionMetadataTest.class,
        MetadataReaderTest.class,
        PackageResourceLoaderTest.class,
        XmlBeanDefinitionReaderTest.class
})
public class TestAll_v4 {
}
