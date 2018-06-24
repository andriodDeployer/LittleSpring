package org.litespring.test.v1;

/**
 * Created by DELL on 2018/6/20.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * user is lwb
 **/


@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ApplicationContextTest.class,
        ResourceTest.class
})
public class TestAll_v1 {

}
