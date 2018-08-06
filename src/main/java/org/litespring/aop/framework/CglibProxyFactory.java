package org.litespring.aop.framework;/**
 * Created by DELL on 2018/8/6.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.aop.Advice;
import org.litespring.aop.AopConfigException;
import org.litespring.util.Assert;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.*;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * user is lwb
 **/


public class CglibProxyFactory implements AopProxyFactory {

//根据不同方法选择不同的拦截器，常量表示拦截器的索引。
    private static final int AOP_PROXY = 0;//使用aopproxy
    private static final int INVOKE_TARGET = 1;//调用target
    private static final int NO_OVERRIDE = 2;//没有overide
    private static final int DISPATCH_TARGET = 3;//转发给目标
    private static final int DISPATCH_ADVISED = 4;//转发给advised
    private static final int INVOKE_EQUALS = 5;//调用equals
    private static final int INVOKE_HASHCODE = 6;//调用hashcode



    protected static final Log logger = LogFactory.getLog(CglibProxyFactory.class);

    private AopConfig aopConfig;

    private Object[] constructorArgs;

    private Class<?>[] constructorArgTypes;


    public CglibProxyFactory(AopConfig aopConfig) throws AopConfigException {
        Assert.notNull(aopConfig,"AdvisedSupport must not be null");
        if(aopConfig.getAdvices().size() == 0){
            throw new AopConfigException("No advisor and no TargetSource specified");
        }
        this.aopConfig = aopConfig;
    }


    public void setConstructorArguments(Object[] constructorArgs, Class<?>[] constructorArgTypes){
        if(constructorArgs == null || constructorArgTypes == null){
            throw new IllegalArgumentException("Both 'constructorArgs' and 'constructorArgTypes' must not be null");
        }

        if(constructorArgs.length != constructorArgTypes.length){
            throw new IllegalArgumentException("Number of 'constructorArgs' ("+constructorArgs.length+") must match number of 'constructorArgTypes' ("+constructorArgTypes.length+")");
        }
        this.constructorArgs = constructorArgs;
        this.constructorArgTypes = constructorArgTypes;
    }


    public Object getProxy() {
        return getProxy(null);
    }

    public Object getProxy(ClassLoader classLoader) {
        if(logger.isDebugEnabled()){
            logger.debug("Creating CGLIB proxy: target source is "+ this.aopConfig.getTargetClass());
        }

        try {
            Class<?> rootClass = this.aopConfig.getTargetClass();

            //configure CGLIB Enhancer
            Enhancer enhancer = new Enhancer();
            if(classLoader != null){
                enhancer.setClassLoader(classLoader);
            }

            enhancer.setSuperclass(rootClass);
            enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);//指定生成代理名字的策略。
            enhancer.setInterceptDuringConstruction(false);

            Callback[] callbacks = getCallBacks(rootClass);
            Class<?>[] types = new Class<?>[callbacks.length];
            for(int x = 0; x < types.length; x++){
                types[x] = callbacks[x].getClass();
            }

            enhancer.setCallbackFilter(new ProxyCallBackFilter(this.aopConfig));
            enhancer.setCallbacks(callbacks);
            enhancer.setCallbackTypes(types);

            Object proxy;
            if(this.constructorArgs != null){
                proxy = enhancer.create(this.constructorArgTypes,this.constructorArgs);
            }else{
                proxy = enhancer.create();
            }

            return proxy;

        }catch (CodeGenerationException ex){
            throw new AopConfigException("Could not generate CGLIB subclass of class [" + this.aopConfig.getTargetClass() + "]:"+
                    " Common causes of this problem include using a final class or a non-visible class",ex);
        } catch (IllegalArgumentException ex){
            throw new AopConfigException("Could not generate CGLIB subclass if class [" + this.aopConfig.getTargetClass() + "]:"+
            " Common causes if this problem include useing a final class or a non-visible class",ex);
        }
        catch (Exception ex){
            throw new AopConfigException("Unexpected AOP exception", ex);
        }
    }

    private Callback[] getCallBacks(Class<?> rootClass) {
        Callback aopInterceptor = new DynamicAdvisedInterceptor(this.aopConfig);

        //Callback targetInterceptor = new StaticUnadvisedExposedInterceptor(this.advised.getTargetObject());
        //Callback targetDispatcher = new StaticDispatcher(this.advised.getTargetObject());

        Callback[] callBacks = new Callback[]{
                aopInterceptor
        };
        return callBacks;

    }


    private static class DynamicAdvisedInterceptor implements MethodInterceptor,Serializable{
        private final AopConfig aopconig;

        public DynamicAdvisedInterceptor(AopConfig aopConfig) {
            this.aopconig = aopConfig;
        }


        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Object target = this.aopconig.getTargetObject();
            List<Advice> chain = this.aopconig.getAdvices(method);
            Object retVal;
            if(chain.isEmpty() && Modifier.isPublic(method.getModifiers())){
                //retVal = methodProxy.invoke(o,objects);
                retVal = methodProxy.invoke(target,objects);
            }else{
                List<org.aopalliance.intercept.MethodInterceptor> interceptors = new ArrayList<org.aopalliance.intercept.MethodInterceptor>();
                interceptors.addAll(chain);
                retVal = new ReflectiveMethodInvocation(target,method,objects,interceptors);

            }
            return retVal;
        }

    }

    private static class ProxyCallBackFilter implements CallbackFilter{

        private AopConfig aopconfig;

        public ProxyCallBackFilter(AopConfig aopconfig) {
            this.aopconfig = aopconfig;
        }

        public int accept(Method method) {
            return AOP_PROXY;
        }
    }
}
