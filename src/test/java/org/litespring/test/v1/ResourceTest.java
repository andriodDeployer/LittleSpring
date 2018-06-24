package org.litespring.test.v1;
/**
 * Created by DELL on 2018/6/20.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.FileSystemResource;
import org.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * user is lwb
 **/


public class ResourceTest {

    @Test
    public void classPathResourceTest(){
        Resource resource = new ClassPathResource("petstore-v1.xml");
        InputStream is = null;
        try {
            is = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(is);
    }

    @Test
    public void fileSystemResourceTest(){
        Resource resource = new FileSystemResource("D:\\workRequire\\workspace\\ideaWorkSpace\\LittleSpring\\src\\test\\resources\\petstore-v1.xml");
        InputStream in = null;
        try {
            in = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(in);
    }

}
