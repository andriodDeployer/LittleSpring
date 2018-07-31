package org.litespring.context.support;
/**
 * Created by DELL on 2018/6/20.
 */

import org.litespring.beans.factory.NoSuchBeanDefinitionException;
import org.litespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

/**
 * user is lwb
 **/


public abstract class AbstractApplicationContext implements ApplicationContext{

    private DefaultBeanFactory factory;
    private ClassLoader classLoader;


    public AbstractApplicationContext(String path){
        this(path,(ClassLoader)null);

    }

    public AbstractApplicationContext(String path,ClassLoader classLoader){
        setBeanClassLoader(classLoader==null?ClassUtils.getDefaultClassLoader():classLoader);
        factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = getResource(path);//模板方法
        reader.loadBeanDefinitions(resource);
        factory.setBeanClassLoader(getBeanClassLoader());
        registerBeanPostProcessor(factory);

    }

    protected abstract Resource getResource(String path);

    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    protected void registerBeanPostProcessor(ConfigurableBeanFactory factory){
        AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
        processor.setBeanFactory(factory);
        factory.addBeanPostProcessor(processor);
    }

    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return null;
    }
}
