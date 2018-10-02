package org.lispring.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

	public boolean matchs(Method method);
	
}
