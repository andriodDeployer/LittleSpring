package org.litespring.aop;

/**
 * Created by DELL on 2018/7/31.
 */
public interface PointCut {

    MethodMatcher getMethodMatcher();
    String getExpression();


}
