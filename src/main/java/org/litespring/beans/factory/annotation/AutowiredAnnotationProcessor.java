package org.litespring.beans.factory.annotation;

import org.litespring.beans.BeansException;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.AutowireCapableBeanFactory;
import org.litespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.litespring.core.annotation.AnnotationUtils;
import org.litespring.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

//这个类的作用是对指定的类需要注入的字段，进行标记，并进行注入。说白了就是对property进行初始化
public class AutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor {
	
	private AutowireCapableBeanFactory beanFactory;
	private String requiredParameterName = "required";
	private boolean requiredParameterValue = true;
	
	private final Set<Class<? extends Annotation>> autowiredAnnotationTypes =
			new LinkedHashSet<Class<? extends Annotation>>();
	
	public AutowiredAnnotationProcessor(){
		this.autowiredAnnotationTypes.add(Autowired.class);
	}

	//通过class找到指定的的field(被autowired修饰的字段)，将指定的field封装成InjectionElement，最后封装成InjectionMetadata。
	public InjectionMetadata buildAutowiringMetadata(Class<?> clazz) {
		LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
		Class<?> targetClass = clazz;

		do {
			LinkedList<InjectionElement> currElements = new LinkedList<InjectionElement>();
			for (Field field : targetClass.getDeclaredFields()) {
				Annotation ann = findAutowiredAnnotation(field);//获取field上指定注解类型的注解。这里主要用来判断一个字段上是否有Autowired注解，目前只对带有antowired注解的字段进行注入。
				if (ann != null) {
					if (Modifier.isStatic(field.getModifiers())) {
						continue;
					}
					boolean required = determineRequiredStatus(ann);
					currElements.add(new AutowiredFieldElement(field, required,beanFactory));
				}
			}
			for (Method method : targetClass.getDeclaredMethods()) {
				//TODO 处理方法注入
			}
			elements.addAll(0, currElements);
			targetClass = targetClass.getSuperclass();
		}
		while (targetClass != null && targetClass != Object.class);

		return new InjectionMetadata(clazz, elements);
	}

	//判断注解中是否有requiredParamterName指定的方法，如果没有，返回true，如果有，获取其返回值。就是为了判断注解中的required属性是否为true。
	protected boolean determineRequiredStatus(Annotation ann) {
		try {
			Method method = ReflectionUtils.findMethod(ann.annotationType(), this.requiredParameterName);
			if (method == null) {
				// Annotations like @Inject and @Value don't have a method (attribute) named "required"
				// -> default to required status
				return true;
			}
			return (this.requiredParameterValue == (Boolean) ReflectionUtils.invokeMethod(method, ann));
		}
		catch (Exception ex) {
			// An exception was thrown during reflective invocation of the required attribute
			// -> default to required status
			return true;
		}
	}
	
	private Annotation findAutowiredAnnotation(AccessibleObject ao) {
		for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
			Annotation ann = AnnotationUtils.getAnnotation(ao, type);
			if (ann != null) {
				return ann;
			}
		}
		return null;
	}
	public void setBeanFactory(AutowireCapableBeanFactory beanFactory){
		this.beanFactory = beanFactory;
	}




	public Object beforeInitialization(Object bean, String beanName) throws BeansException {
		//TODO do nothing
		return null;
	}

	public Object afterInitializeation(Object bean, String beanName) throws BeansException {
		//TODO do nothing
		return null;
	}

	public Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		//TODO do nothing
		return null;
	}

	public boolean afterInstantiation(Object bean, String beanName) throws BeansException {
		//TODO do noting
		return false;
	}

	public void postProcessorPropertyValues(Object bean, String beanName) throws BeansException {
		try {
			InjectionMetadata metadata = buildAutowiringMetadata(bean.getClass());
			metadata.inject(bean);
		} catch (Throwable ex){
			throw new BeanCreationException(beanName,"Injection of autowired dependencies failed");
		}

	}
}
