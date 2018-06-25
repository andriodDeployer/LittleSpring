package org.litespring.beans.factory.xml;

/**
 * Created by DELL on 2018/6/20.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionRegistry;
import org.litespring.beans.factory.support.GenericBeanDefinition;
import org.litespring.core.io.Resource;
import org.litespring.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


/**
 * user is lwb
 *
 * 解析xml文件，从中读取去beandefinition，将beandefinition注册到factory中。
 **/


public class XMLBeanDefinitionReader {

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String ID_ATTRIBUTE = "id";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";


    BeanDefinitionRegistry registry;
   // protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final Log logger = LogFactory.getLog(getClass());


    public XMLBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream is = null;
        try {
                is = resource.getInputStream();
                SAXReader reader = new SAXReader();
                Document doc = reader.read(is);
                Element root = doc.getRootElement();//<beans>
                Iterator<Element> iter = root.elementIterator();
                while(iter.hasNext()){
                    Element ele = iter.next();
                    String beanId = ele.attributeValue(ID_ATTRIBUTE);
                    String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                    String scope = ele.attributeValue(SCOPE_ATTRIBUTE);
                    BeanDefinition bd = new GenericBeanDefinition(beanId,beanClassName);
                    if(scope != null){
                        bd.setScope(scope);
                    }
                    registry.registerBeanDefinition(beanId,bd);//不断的调用registerDefinition，将解析出来的beanDefinition放到beanFactory中
                }

        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document",e);
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void parsePropertyElement(Element beanElem, BeanDefinition bd){
        Iterator iterator = beanElem.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()){
            Element propElem = (Element) iterator.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if(!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object val = parsePropertyValue(propElem,bd,propertyName);

             //propertyValue = propElem.attributeValue(VALUE_ATTRIBUTE);
        }
    }

    public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName){
        String elementName = (propertyName != null) ? "<property> element for property '" + propertyName + "'":
                "<constructor-arg> element";

        //是否可以针对propertyValue中value的类型不同，创建不同的propertyValue类型。
        boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE) == null);
        boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE) == null);
        if(hasRefAttribute){
            String refName = ele.attributeValue(REF_ATTRIBUTE);
            if(!StringUtils.hasText(refName)){
                logger.error(elementName + " contains empty 'ref' attribute");//仅仅只是输出了一条日志，因为不会一个prperty加载出错，使整个程序崩溃
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        }else{
            TypedStringValue valueHolder = new TypedStringValue(ele.attributeValue(VALUE_ATTRIBUTE));
        }
        return null;
    }





}
