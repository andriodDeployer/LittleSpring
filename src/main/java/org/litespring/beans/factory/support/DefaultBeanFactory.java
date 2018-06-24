package org.litespring.beans.factory.support;
/**
 * Created by DELL on 2018/6/19.
 */

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * user is
 * beanfactory的职责：
 *  1.解析xml
 *  2.通过反射创建bean实例
 *
 **/
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory,BeanDefinitionRegistry{

    private final Map<String,BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();
    private ClassLoader classLoader;

    public DefaultBeanFactory(){}


    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if(bd == null){
           throw new BeanCreationException("Bean Definition does not exist");//因为这是一个runtimeException，不需要进行try catch；
        }

        if(bd.isSingleton()){
            Object bean = getSingleton(beanId);
            if(bean == null){
                bean = createBean(bd);
                registerSingleton(beanId,bean);
            }
            return bean;
        }
        return createBean(bd);
    }

    private Object createBean(BeanDefinition bd) {
        ClassLoader loader = getBeanClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clazz = loader.loadClass(beanClassName);//将指定的一个类加载到内存中，并获取到这个类的class文件
            return clazz.newInstance();//目前只支持午餐构造
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeanCreationException("create bean for " + beanClassName + " accure error ");
        }
    }


    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    public void registerBeanDefinition(String beanId, BeanDefinition bd) {
        beanDefinitionMap.put(beanId,bd);

    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;

    }

    public ClassLoader getBeanClassLoader() {
        return (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }
}
