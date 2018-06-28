package org.litespring.test;/**
 * Created by DELL on 2018/6/27.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.litespring.test.v1.TestAll_v1;
import org.litespring.test.v2.TestAll_v2;

/**
 * user is lwb
 **/

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAll_v2.class,
        TestAll_v1.class
})
public class TestAll {
}
