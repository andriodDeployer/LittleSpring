package org.litespring.beans.factory;
/**
 * Created by DELL on 2018/8/10.
 */

/**
 * user is lwb
 **/

/**
 * 这个接口的名字和BeanFactory虽然很相似，但是两者却有本质上的不同，
 *  BeanFactory：是ioc容器实现的接口，让实现类可以从容器中，取出bean。
 *  FactoryBean：是一个bean可以实现的接口，实现了该接口的bean仍然是一个bean，只不过有一点特殊。
 *      特殊在：1.站在这个bean自身的角度来说：实现这个接口的bean可以做为其他bean的工厂(工厂模式)，因为器getObject方法可以返回其他类型的bean
 *             2.站在ioc容器的角度来说：实现这个接口的bean具有装饰的功能，因为按照实现类的名称，从ioc容器中找去这个bean时，可以返回这个bean的getObject方法的返回值，在这个getObject中可以对bean进行增强等操作。
 *             3.站在用户角度来说：实现这个接口的bean具有模板方法的作用。通常通过ioc获取这个bean时，如果发现这个bean实现了FactoryBean接口的话，，会返回给用户这个bean的getObject方法的返回值。
 *               这个流程是是固定的，固定在spring的框架中了，实现类可以通过实现getObject方法，来动态修改bean的真正值。
 */

public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<T> getObjectTypes();

}
