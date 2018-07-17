package org.litespring.test.v4;/**
 * Created by DELL on 2018/7/17.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.Resource;
import org.litespring.core.io.support.PackageResouceLoader;

import java.io.IOException;

/**
 * user is lwb
 **/


public class PackageResourceLoaderTest {

    /**
     * 测试PackageResouceLoader的功能:获取指定包下所有class文件对应的Resouce
     * @throws IOException
     */
    @Test
    public void testGetResouces() throws IOException{
        PackageResouceLoader loader = new PackageResouceLoader();
        Resource[] resouces = loader.getResouces("org.litespring.dao.v4");
        Assert.assertEquals(2,resouces.length);
    }
}
