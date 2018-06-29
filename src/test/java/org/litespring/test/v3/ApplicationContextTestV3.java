package org.litespring.test.v3;
/**
 * Created by DELL on 2018/6/29.
 */

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v3.PetStoreService;

/**
 * user is lwb
 **/


public class ApplicationContextTestV3 {


    @Test
    public void testGetBeanProperty(){

        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");


        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());
        Assert.assertEquals(1,petStoreService.getVersion());

    }

}
