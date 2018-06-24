package org.litespring.context.support;

/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

/**
 * user is
 **/


public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    protected Resource getResource(String path) {
        return new FileSystemResource(path);
    }

}
