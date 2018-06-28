package org.litespring.test.v2;/**
 * Created by DELL on 2018/6/27.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * user is lwb
 **/

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV2.class,
        BeanDefinitionTestV2.class,
        BeanDefinitionValueResolverTest2.class,
        CustomBooleanEditorTest.class,
        CustomNumberEditorTest.class,
        TypeConverterTest.class
})
public class TestAll_v2 {
}
