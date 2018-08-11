package org.litespring.aop.config;/**
 * Created by DELL on 2018/8/11.
 */

import org.dom4j.Element;
import org.litespring.aop.aspectJ.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectJ.AspectJAfterThrowingAdvice;
import org.litespring.aop.aspectJ.AspectJBeforeAdvice;
import org.litespring.aop.aspectJ.AspectJExpressionPointcut;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.support.BeanDefinitionReaderUtils;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * user is
 **/

//将aop标签进行解析，并进行包装和合成，形成beandefinition。之所以进行包装/合成，是因为aop标签不符合原始bean定义结构。
public class ConfigBeanDefinitionParser {

    //针对不同的标签进行常量化
    private static final String ASPECT = "aspect";
    private static final String EXPRESSION = "expression";
    private static final String ID = "id";
    private static final String POINTCUT = "pointcut";
    private static final String POINTCUT_REF = "ref";
    private static final String BEFORE = "before";
    private static final String AFTER = "after";
    private static final String AFTER_RETURNING_ELEMENT = "after-returning";
    private static final String AFTER_THROWING_ELEMENT = "after-throwing";
    private static final String AROUND = "around";
    private static final String ASPECT_NAME_PROPERTY = "aspectName";



    //对ele指定的内容进行解析，解析成一个beandefiniton，并存放到registry中
    public BeanDefinition parse(Element ele, BeanDefinitionRegistry registry) { //ele:config
        List<Element> elements = ele.elements();//element:aspect
        for(Element element : elements){
            String localName = element.getName();
            
            if(ASPECT.equals(localName)){
                parseAspect(element,registry);
            }
        }
        return null;
    }

    /**
     * 解析Aspect
     * @param aspectElement
     * @param registry
     */
    private void parseAspect(Element aspectElement, BeanDefinitionRegistry registry) {
        String aspectId = aspectElement.attributeValue(ID);
        String aspectName = aspectElement.attributeValue(POINTCUT_REF);
        List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();
        List<RuntimeBeanReference> beanReferences = new ArrayList<RuntimeBeanReference>();
        List<Element> eleList = aspectElement.elements();//分别表示pointcut,before,after-returning,after-throwing
        boolean adviceFoundAlready = false;

        //for循环用来解析advice
        for(int i = 0; i<eleList.size(); i++){
            Element ele = eleList.get(i);
            if(isAdviceNode(ele)){
                if(!adviceFoundAlready){
                    adviceFoundAlready = true;
                    if(!StringUtils.hasText(aspectName)){
                        return;
                    }
                    beanReferences.add(new RuntimeBeanReference(aspectName));//beanReferences中保留了advice依赖前面逻辑对象。也即是增强方法的所有者
                }

                //生成advice对象
                GenericBeanDefinition advisorDefinition = parseAdvice(aspectName,i,aspectElement,ele,registry,beanDefinitions,beanReferences);
                beanDefinitions.add(advisorDefinition);
            }
        }

        //解析pointcut
        List<Element> pointcuts = aspectElement.elements(POINTCUT);
        for(Element pointcutElement : pointcuts){
            parsePointcut(pointcutElement,registry);
        }
    }


    /**
     * Return {@code true} if the supplied node describes an advice type. May be one of:
     * '{@code before}','{@code after}','{@code after-returning}','{@code after-throwing}' or '{@code around}'
     * @param ele
     * @return
     */
    private boolean isAdviceNode(Element ele) {
        String name = ele.getName();
        return (BEFORE.equals(name) || AFTER.equals(name) || AFTER_THROWING_ELEMENT.equals(name) ||
                AFTER_RETURNING_ELEMENT.equals(name) || AROUND.equals(name));
    }


    /**
     * 解析advice
     * @param aspectName
     * @param oreder
     * @param aspectElement
     * @param adviceElement
     * @param registry
     * @param beanDefinitions
     * @param beanReferences
     * @return
     */
    private GenericBeanDefinition parseAdvice(String aspectName, int oreder, Element aspectElement,
                                              Element adviceElement, BeanDefinitionRegistry registry,
                                              List<BeanDefinition> beanDefinitions, List<RuntimeBeanReference> beanReferences) {
        //这个beandefinion是人工合成的，下面的propertyvalue不是从xml中读取出来的，是写死的，所以MethodLocatingFactory中的属性名是不能发生变化的，如果发生变化了，
        // 那么这个合成的beandefinition就无法经过beanfactory生成MethodLocatingFactory对象了
        //MethodLocatingFactory对象
        GenericBeanDefinition methodDefinition = new GenericBeanDefinition(MethodLocatingFactory.class);
        methodDefinition.getPropertyValues().add(new PropertyValue("targetBeanName",aspectElement));
        methodDefinition.getPropertyValues().add(new PropertyValue("methodName",adviceElement.attributeValue("method")));
        methodDefinition.setSynthtic(true);

        //aspect对象
        GenericBeanDefinition aspectFactoryDef = new GenericBeanDefinition(AspectInstanceFactory.class);
        aspectFactoryDef.getPropertyValues().add(new PropertyValue("aspectBeanName",aspectName));
        aspectFactoryDef.setSynthtic(true);

        GenericBeanDefinition adviceDef = createAdviceDefinition(
                adviceElement,registry,aspectName,oreder,methodDefinition,aspectFactoryDef,
                beanDefinitions,beanReferences);
        adviceDef.setSynthtic(true);

        BeanDefinitionReaderUtils.registerWithGeneratedName(adviceDef,registry);
        return adviceDef;
    }


    /**
     * 解析pointcut
     * @param pointcutElement
     * @param registry
     * @return
     */
    private GenericBeanDefinition parsePointcut(Element pointcutElement, BeanDefinitionRegistry registry) {
        String id = pointcutElement.attributeValue(ID);
        String express = pointcutElement.attributeValue(EXPRESSION);
        GenericBeanDefinition pointcutBeanDefinition = null;
        pointcutBeanDefinition = createPointcutDefinition(express);
        String pointcutBeanName = id;
        if(StringUtils.hasText(pointcutBeanName)){
            registry.registerBeanDefinition(pointcutBeanName,pointcutBeanDefinition);
        }else{
            BeanDefinitionReaderUtils.registerWithGeneratedName(pointcutBeanDefinition,registry);
        }
        return pointcutBeanDefinition;
    }


    /**
     * 创建adviceDefinition
     * @param adviceElement
     * @param registry
     * @param aspectName
     * @param oreder
     * @param methodDefinition
     * @param aspectFactoryDef
     * @param beanDefinitions
     * @param beanReferences
     * @return
     */
    private GenericBeanDefinition createAdviceDefinition(Element adviceElement, BeanDefinitionRegistry registry,
                                                         String aspectName, int oreder, GenericBeanDefinition methodDefinition,
                                                         GenericBeanDefinition aspectFactoryDef, List<BeanDefinition> beanDefinitions,
                                                         List<RuntimeBeanReference> beanReferences) {
        GenericBeanDefinition adviceDefinition = new GenericBeanDefinition(getAdviceClass(adviceElement));
        adviceDefinition.getPropertyValues().add(new PropertyValue(ASPECT_NAME_PROPERTY,aspectName));

        ConstructorArgument constructorArgument = adviceDefinition.getConstructorArgument();
        constructorArgument.addArgumentValue(methodDefinition);//增加method参数

        //增加pointcut参数
        Object pointcut = parsePointcutProperty(adviceElement);
        if(pointcut instanceof BeanDefinition) {
            constructorArgument.addArgumentValue(pointcut);
            beanDefinitions.add((BeanDefinition) pointcut);
        }else if(pointcut instanceof String){
            RuntimeBeanReference pointcutRef = new RuntimeBeanReference((String) pointcut);
            constructorArgument.addArgumentValue(pointcutRef);
            beanReferences.add(pointcutRef);
        }

        constructorArgument.addArgumentValue(aspectFactoryDef);//增加aspect参数
        return adviceDefinition;
    }

    /**
     * 解析advice的pointcut属性
     * @param element
     * @return
     */
    private Object parsePointcutProperty(Element element) {

        if((element.attribute(POINTCUT) == null) && (element.attribute(POINTCUT_REF) == null)){
            return null;
        } else if(element.attribute(POINTCUT) != null){
            String expression = element.attributeValue(POINTCUT);
            GenericBeanDefinition pointcutDefinition = createPointcutDefinition(expression);
            return pointcutDefinition;
        }else if(element.attribute(POINTCUT_REF)!=null){
            String pointcutRef = element.attributeValue(POINTCUT_REF);
            if(!StringUtils.hasText(pointcutRef)){
                return null;
            }
            return pointcutRef;
        }else{
            return null;
        }


    }

    /**
     * 创建pointcutDefinition
     * @param expression
     * @return
     */
    private GenericBeanDefinition createPointcutDefinition(String expression) {
        GenericBeanDefinition poinitcutDefinition = new GenericBeanDefinition(AspectJExpressionPointcut.class);
        poinitcutDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        poinitcutDefinition.setSynthtic(true);
        poinitcutDefinition.getPropertyValues().add(new PropertyValue(EXPRESSION,expression));
        return poinitcutDefinition;

    }

    private Class<?> getAdviceClass(Element adviceElement) {
        String elementName = adviceElement.getName();
        if(BEFORE.equals(elementName)){
            return AspectJBeforeAdvice.class;
        }
        if(AFTER_RETURNING_ELEMENT.equals(elementName)){
            return AspectJAfterReturningAdvice.class;
        }
        if(AFTER_THROWING_ELEMENT.equals(elementName)){
            return AspectJAfterThrowingAdvice.class;
        }
        throw  new IllegalArgumentException("Unknown advice kind [ "+ elementName + "}");
    }


}
