package org.litespring.aop.aspectJ;/**
 * Created by DELL on 2018/7/31.
 */

import org.aspectj.weaver.reflect.ReflectionWorld;
import org.aspectj.weaver.tools.*;
import org.litespring.aop.MethodMatcher;
import org.litespring.aop.PointCut;
import org.litespring.util.ClassUtils;
import org.litespring.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * user is
 **/


public class AspectJExpressionPointcut implements PointCut, MethodMatcher{
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();
    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);//支持execution解析
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);//支持arg解析
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);//支持注解
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    private String expression;
    private PointcutExpression pointcutExpression;
    private ClassLoader pointClassLoader;
    public AspectJExpressionPointcut(){

    }

    public MethodMatcher getMethodMatcher() {
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public boolean matches(Method method) {
        checkReadyToMatch();
        ShadowMatch shadowMatch = getShadowMatch(method);

        return false;
    }

    private ShadowMatch getShadowMatch(Method method) {

        ShadowMatch shadowMatch = null;
        try {
            shadowMatch = this.pointcutExpression.matchesMethodExecution(method);
        }
        catch (ReflectionWorld.ReflectionWorldException ex) {

            throw new RuntimeException("not implemented yet");
			/*try {
				fallbackExpression = getFallbackPointcutExpression(methodToMatch.getDeclaringClass());
				if (fallbackExpression != null) {
					shadowMatch = fallbackExpression.matchesMethodExecution(methodToMatch);
				}
			}
			catch (ReflectionWorldException ex2) {
				fallbackExpression = null;
			}*/
        }
        return shadowMatch;

    }


    private void checkReadyToMatch() {
        if(getExpression() == null) {
            throw new IllegalStateException("Must set property 'Expression' before attempting to");
        }
        if(this.pointcutExpression == null){
            this.pointClassLoader = ClassUtils.getDefaultClassLoader();
            this.pointcutExpression = bulidPointcutExpression(this.pointClassLoader);
        }
        
    }

    private PointcutExpression bulidPointcutExpression(ClassLoader pointClassLoader) {
        PointcutParser parser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(
          SUPPORTED_PRIMITIVES,pointClassLoader);
        return parser.parsePointcutExpression(replaceBooleanOperators(getExpression()),
                null, new PointcutParameter[0]);
    }

    private String replaceBooleanOperators(String pcExpr) {
        String result = StringUtils.replace(pcExpr, " and ", " && ");
        result = StringUtils.replace(result, " or ", " || ");
        result = StringUtils.replace(result, " not ", " ! ");
        return result;
    }

}
