package org.lispring.aop;

import org.aopalliance.intercept.MethodInterceptor;

public interface Advice extends MethodInterceptor{

	public PointCut getPointcut();
}
