package org.litespring.test.v5;/**
 * Created by DELL on 2018/7/31.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * user is lwb
 **/

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //ApplicationContext.class,
        MethodLocatingFactoryTest.class,
        PointCutTest.class
})
public class TestAll_v5 {
}
