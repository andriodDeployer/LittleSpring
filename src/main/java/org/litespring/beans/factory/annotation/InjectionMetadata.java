package org.litespring.beans.factory.annotation;/**
 * Created by DELL on 2018/7/28.
 */

/**
 * user is
 **/

/**
 * 封装了一个类的所有injectionElement
 */
public class InjectionMetadata {
    private final Class<?> targetClass;//设置为final说明一个injectionMetadata的生命周期中，只能完成对一个类的依赖进行注入
    private List<InjectionElement> injectionElements;

    public InjectionMetadata(Class<?> targetClass, List<InjectionElement> injectionElements) {
        this.targetClass = targetClass;
        this.injectionElements = injectionElements;
    }

    public List<InjectionElement> getInjectionElements() {
        return injectionElements;
    }

    public void inject(Object target){
        if(injectionElements == null || injectionElements.isEmpty()){
            return;
        }
        for(InjectionElement ele : injectionElements){
            ele.inject(target);
        }
    }
}
