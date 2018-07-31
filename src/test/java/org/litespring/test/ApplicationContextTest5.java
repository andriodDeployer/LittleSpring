package org.litespring.test;/**
 * Created by DELL on 2018/7/31.
 */

/**
 * user is lwb
 **/

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v5.PetStoreService;

/**
 * 在v5包中，先写一个高层次的测试例子，通过这个高层次的需求，不断驱动出低层次的测试用例(各个小模块功能)
 * 当每个每个低层次的各个测试用例都测试成功，高层次的功能就是多个低层次模块堆积的结果。
 * 一定要保证各个低层次模块功能的正确性稳定性，也就是小步快跑。
 */

public class ApplicationContextTest5 {

    @Test
    public void testPlaceOrder(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v5.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStore");
        Assert.assertNotNull(petStoreService.getItemDao());
        Assert.assertNotNull(petStoreService.getAccountDao());

        petStoreService.placeOrder();

    }

}
