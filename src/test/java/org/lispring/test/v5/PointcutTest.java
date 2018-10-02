package org.lispring.test.v5;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.aop.MethodMatcher;
import org.lispring.aop.aspectj.AspectJExpressionPointcut;
import org.lispring.dao.AccountDao;
import org.lispring.service.v5.PetStoService;

/**
 * 
 * 处理 xml文件中 pointcust
 * 
 * @author Administrator
 *
 */
public class PointcutTest {

	@Test
	public void testPointCut() throws NoSuchMethodException, SecurityException {
		String expression = "execution(* org.lispring.service.v5.*.placeOrder(..))";
		AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
		pc.setExpression(expression);
		
		MethodMatcher mm = pc.getMethodMatcher();
		
		{
			Class<?> targetClass = PetStoService.class;
			
			Method method1 = targetClass.getMethod("placeOrder");
			Assert.assertTrue(mm.matchs(method1));
			
			Method method2 = targetClass.getMethod("getAccountDao");
			Assert.assertFalse(mm.matchs(method2));
		}
		
		{
			Class<?> targetClass = org.lispring.service.PetStoService.class;
			
			Method method1 = targetClass.getMethod("getAccountDao");
			Assert.assertFalse(mm.matchs(method1));
			
		}
	}

}
