package org.litespring.aop.config;/**
 * Created by DELL on 2018/8/10.
 */

import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.BeanFactoryAware;
import org.litespring.util.StringUtils;

/**
 * user is lwb
 **/

/**
 * 封装了一个beanFactory，这个factory的功能比较单一只能获取aspetctBeanName指定的bean.
 */
public class AspectInstanceFactory implements BeanFactoryAware {

    private String aspectBeanName;
    private BeanFactory beanFactory;

    public void setAspetcBeanName(String aspetcBeanName){
        this.aspectBeanName = aspetcBeanName;
    }

    public void setBeanFactory(BeanFactory beanFactory){
        this.beanFactory = beanFactory;
        if(!StringUtils.hasText(this.aspectBeanName)) {
            throw new IllegalArgumentException("'aspectBeanName' is required");
        }
    }

    public Object getAspectInstance() throws Exception{
        return this.beanFactory.getBean(this.aspectBeanName);
    }


}
