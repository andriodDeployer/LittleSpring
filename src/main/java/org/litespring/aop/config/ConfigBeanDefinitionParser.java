package org.litespring.aop.config;/**
 * Created by DELL on 2018/8/11.
 */

import org.dom4j.Element;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;

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
    private static final String AFTER_THROWING = "after-throwing";
    private static final String AROUND = "around";
    private static final String ASPECT_NAME_PROPERTY = "aspectName";



    //对ele指定的内容进行解析，解析成一个beandefiniton，并存放到registry中
    public void parse(Element ele,  BeanDefinitionRegistry registry) {

    }
}
