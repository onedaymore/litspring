package org.lispring.test.v5;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.lispring.aop.aspectj.AspectJAfterReturnAdvice;
import org.lispring.aop.aspectj.AspectJBeforeAdvice;
import org.lispring.aop.framework.ReflectiveMethodInvocation;
import org.lispring.service.v5.PetStoService;
import org.lispring.tx.TransactionManager;

/**
 * 指定次序的链式调用
 * 
 * @author Administrator
 *
 */
public class ReflectiveMethodInvocationTest {

	private AspectJBeforeAdvice beforeAdvice = null;
	private AspectJAfterReturnAdvice afterAdvice = null;
	private PetStoService petStoService = null;
	private TransactionManager tx = null;
	
	@Before
	public void setup() throws NoSuchMethodException, SecurityException {
		petStoService = new PetStoService();
		tx = new TransactionManager();
		
		beforeAdvice = new AspectJBeforeAdvice(
				TransactionManager.class.getMethod("start"),
				null,
				tx
				);
		afterAdvice = new AspectJAfterReturnAdvice(
				TransactionManager.class.getMethod("commit"),
				null,
				tx
				);
	}
	
	@Test
	public void testReflectiveMethodInvocation() throws Throwable {
		Method targetMethod = PetStoService.class.getMethod("placeOrder");
		List<MethodInterceptor> mis = new ArrayList<>();
		mis.add(beforeAdvice);
		mis.add(afterAdvice);
		
		ReflectiveMethodInvocation rmi = new ReflectiveMethodInvocation(
				petStoService, targetMethod, new Object[0], mis
				);
		rmi.proceed();
	}
}
