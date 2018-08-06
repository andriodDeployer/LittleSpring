package org.litespring.test.v5;/**
 * Created by DELL on 2018/8/6.
 */

import org.junit.Test;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * user is lwb
 **/


public class CGLibTest {


    @Test
    public void testCallBack(){

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(PetStoreService.class);
        enhancer.setCallback(new TranscationInterceptor());
        PetStoreService petStoreService = (PetStoreService) enhancer.create();//产生的petStore是原来petstoreServic的子类。
       // petStoreService.placeOrder();

        petStoreService.toString();
    }





    @Test
    public void testFileter(){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PetStoreService.class);
        enhancer.setInterceptDuringConstruction(false);

        Callback[] callbacks = new Callback[]{new TranscationInterceptor(),NoOp.INSTANCE};

        Class<?>[] types = new Class<?>[callbacks.length];
        for(int x = 0; x<types.length ; x++){
            types[x] = callbacks[x].getClass();
        }


        enhancer.setCallbackFilter(new ProxyCallbackFilter());
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackTypes(types);

        PetStoreService petStoreService = (PetStoreService) enhancer.create();//创建这个代理对象时，代理对象中，每个方法按照何种方式增强，遵循filter指定的规则来确定。也就是产生增强对象后，每个方式的增强方式(MethodInterceptor)就已经确定了。
        petStoreService.placeOrder();
        System.out.println(petStoreService.toString());
    }
















    private static class ProxyCallbackFilter implements CallbackFilter{

        public ProxyCallbackFilter() {
        }

        //按照一定的规则，给指定的方法绑定指定的拦截器。
        public int accept(Method method) {
            if(method.getName().startsWith("place")){
                return 0;
            }else{
                return 1;
            }
        }
    }







    //类似与java动态代理中的invocationHandler
    public static class TranscationInterceptor implements MethodInterceptor{
        TransactionManager transactionManager = new TransactionManager();

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

            transactionManager.start();
            Object result = methodProxy.invokeSuper(o,objects);//实际的业务逻辑
            transactionManager.commit();
            return result;

        }
    }













}
