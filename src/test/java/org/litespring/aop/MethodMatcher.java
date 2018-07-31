package org.litespring.aop;

import java.lang.reflect.Method;

/**
 * Created by DELL on 2018/7/31.
 */
public interface MethodMatcher {
    boolean matches(Method method);
}
