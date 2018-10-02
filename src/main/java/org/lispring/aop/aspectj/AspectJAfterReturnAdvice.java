package org.lispring.aop.aspectj;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.lispring.aop.Advice;
import org.lispring.aop.PointCut;

public class AspectJAfterReturnAdvice implements Advice {
	
	private Method adviceMethod;
	private PointCut pc;
	private Object adviceObject;

	public AspectJAfterReturnAdvice(Method method,  AspectJExpressionPointcut pc, Object adviceObject) {
		this.adviceMethod = method;
		this.pc = pc;
		this.adviceObject = adviceObject;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object o = mi.proceed();
		adviceMethod.invoke(adviceObject);
		return o;
	}

	@Override
	public PointCut getPointcut() {
		return pc;
	}

}
