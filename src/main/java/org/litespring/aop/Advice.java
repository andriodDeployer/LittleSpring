package org.litespring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created by DELL on 2018/8/4.
 */
public interface Advice extends MethodInterceptor{
    public PointCut getPointcut();
}
