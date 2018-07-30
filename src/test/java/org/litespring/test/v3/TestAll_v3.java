package org.litespring.test.v3;/**
 * Created by Administrator on 2018-6-30 0030.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * user is lwb
 **/

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV3.class,
        BeanDefinitionTestV3.class,
        ConstructorResolverTest.class
})
public class TestAll_v3 {
}
