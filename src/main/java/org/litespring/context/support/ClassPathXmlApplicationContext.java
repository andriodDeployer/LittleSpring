package org.litespring.context.support;
/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

/**
 * user is
 **/


public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    protected Resource getResource(String path) {
        return new ClassPathResource(path,getBeanClassLoader());
    }
}
