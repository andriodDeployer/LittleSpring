package org.litespring.beans.factory.support;
/**
 * Created by DELL on 2018/6/19.
 */

import org.apache.commons.beanutils.BeanUtils;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
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
        Object bean = instantiateBean(bd);
        //populateBean(bd,bean);
        populateBeanUseCommonBeanUtils(bd,bean);
        return bean;
    }

    private Object instantiateBean(BeanDefinition beanDefinition){
        if(beanDefinition.hasConstructorArgumentValues()){
            ConstructorResolver resolver = new ConstructorResolver(this);
            return resolver.autowireConstructor(beanDefinition);
        }else{
            ClassLoader loader = getBeanClassLoader();
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                Class<?> clazz = loader.loadClass(beanClassName);//将指定的一个类加载到内存中，并获取到这个类的class文件
                return clazz.newInstance();//目前只支持无参构造
            } catch (Exception e) {
                e.printStackTrace();
                throw new BeanCreationException("create bean for " + beanClassName + " accure error ");
            }
        }

    }

    //setter注入
    private void populateBean(BeanDefinition bd,Object bean){
        List<PropertyValue> propertyValues = bd.getPropertyValues();

        if(propertyValues == null || propertyValues.isEmpty()){
            return ;//不需要进行setter注入
        }

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        TypeConverter converter = new SimpleTypeConverter();
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(PropertyValue pv : propertyValues){
                String propertyName = pv.getName();
                Object originalValue = pv.getValue(); //RuntimeBeanReference/TypedStringValue
                Object value = resolver.resolveValueIfNecessary(originalValue);
                //需要对value进行相应的类型转换
                //通过反射对setter进行注入？通过JavaBean规范的工具类Introspector
                for (PropertyDescriptor pd : propertyDescriptors){
                    if(pd.getName().equals(propertyName)){
                        Object convertedValue = converter.convertIfNecessary(value,pd.getPropertyType());
                        Method writeMethod = pd.getWriteMethod();
                        writeMethod.invoke(bean,convertedValue);//writerMethod就是setter方法,同理reader方法就是getter方法,如果没有setter方法的话，那个writeMethod方法就是空的
                        break;
                    }
                }
            }
        }catch (Exception ex){
            throw new BeanCreationException("Failed to obtain BeanInfo for class [ " + bd.getBeanClassName()+" ]");//异常信息不明确,配置文件中ref为空的时候。会在resolveValueIfNessary方法中getbean出现异常。
        }
    }

    public void populateBeanUseCommonBeanUtils(BeanDefinition bd,Object bean){
        List<PropertyValue> propertyValues = bd.getPropertyValues();

        if(propertyValues == null || propertyValues.isEmpty()){
            return ;//不需要进行setter注入
        }

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(PropertyValue pv : propertyValues){
                String propertyName = pv.getName();
                Object originalValue = pv.getValue(); //RuntimeBeanReference/TypedStringValue
                Object value = resolver.resolveValueIfNecessary(originalValue);
                //需要对value进行相应的类型转换
                //通过反射对setter进行注入？使用common-beanutils来实现
                BeanUtils.setProperty(bean,propertyName,value);

            }
        }catch (Exception ex){
            throw new BeanCreationException("Failed to obtain BeanInfo for class [ " + bd.getBeanClassName()+" ]");//异常信息不明确,配置文件中ref为空的时候。会在resolveValueIfNessary方法中getbean出现异常。
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
