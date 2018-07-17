package org.litespring.test.v4;/**
 * Created by DELL on 2018/7/17.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v4.PetStoreService;

/**
 * user is lwb
 **/


public class ApplicationContextTest4 {


    @Test
    public void testGetBeanProperty(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v4.xml");
        PetStoreService storeService = (PetStoreService) ctx.getBean("petStore");

        Assert.assertNotNull(storeService.getItemDao());
        Assert.assertNotNull(storeService.getAccountDao());

    }
}
