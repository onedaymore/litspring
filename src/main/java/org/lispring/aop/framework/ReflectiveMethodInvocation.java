package org.lispring.aop.framework;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ReflectiveMethodInvocation implements MethodInvocation {
	
	protected final Object targetObject;
	protected final Method targetMethod;
	protected final Object[] arguments;
	
	protected final List<MethodInterceptor> interceptors;
	
	private int currentInterceptorIndex = -1;
	
	
	public ReflectiveMethodInvocation(
			Object target, Method method, Object[] arguments,
			List<MethodInterceptor> interceptors) {

		this.targetObject = target;		
		this.targetMethod = method;
		this.arguments = arguments;
		this.interceptors = interceptors;
	}

	@Override
	public Object[] getArguments() {
		return this.arguments!=null ? this.arguments : new Object[0];
	}

	@Override
	public Object proceed() throws Throwable {
		//	所有的拦截器已经调用完成
		if (this.currentInterceptorIndex == this.interceptors.size() - 1) {
			return invokeJoinpoint();
		}

		this.currentInterceptorIndex ++;
		
		MethodInterceptor interceptor =
				this.interceptors.get(this.currentInterceptorIndex);
		
		return interceptor.invoke(this);
	}

	@Override
	public Object getThis() {
		return this.targetObject;
	}

	
	protected Object invokeJoinpoint() throws Throwable {		
		return this.targetMethod.invoke(this.targetObject, this.arguments);
	}
	@Override
	public AccessibleObject getStaticPart() {
		return this.targetMethod;
	}

	@Override
	public Method getMethod() {
		return this.targetMethod;
	}

}
