package org.lispring.aop;

public interface PointCut {

	MethodMatcher getMethodMatcher();
	String getExpression();
}
