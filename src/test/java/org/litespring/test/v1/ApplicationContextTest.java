package org.litespring.test.v1;
/**
 * Created by DELL on 2018/6/20.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;

/**
 * user is lwb
 **/


public class ApplicationContextTest {

    @Test
    public void testGetBeanFromClassPathContext(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService service = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(service);
    }


    @Test
    public void testGetBeanFromFileSystemContext(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext("D:\\workRequire\\workspace\\ideaWorkSpace\\LittleSpring\\src\\test\\resources\\petstore-v1.xml");
        PetStoreService service = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(service);
    }
}
