package org.litespring.aop;/**
 * Created by DELL on 2018/8/4.
 */

import org.aopalliance.intercept.MethodInterceptor;

/**
 * user is lwb
 **/


public interface Advice extends MethodInterceptor {
    PointCut getPointcut();
}
