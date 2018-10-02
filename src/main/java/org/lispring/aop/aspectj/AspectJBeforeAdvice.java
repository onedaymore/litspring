package org.lispring.aop.aspectj;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.lispring.aop.Advice;
import org.lispring.aop.PointCut;

public class AspectJBeforeAdvice implements Advice {
	
	private Method adviceMethod;
	private PointCut pc;
	private Object adviceObject;

	
	public AspectJBeforeAdvice(Method method, AspectJExpressionPointcut pc, Object adviceObject) {
		this.adviceMethod = method;
		this.pc = pc;
		this.adviceObject = adviceObject;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		adviceMethod.invoke(adviceObject);
		Object o = mi.proceed();
		return o;
	}

	@Override
	public PointCut getPointcut() {
		return pc;
	}

}
