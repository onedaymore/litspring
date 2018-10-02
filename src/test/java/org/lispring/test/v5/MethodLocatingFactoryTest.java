package org.lispring.test.v5;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.lispring.aop.config.MethodLocatingFactory;
import org.lispring.beans.factory.support.DefaultBeanFactory;
import org.lispring.beans.factory.xml.XmlBeanDefinitionReader;
import org.lispring.core.io.Resource;
import org.lispring.core.io.support.ClassPathResource;
import org.lispring.tx.TransactionManager;

/**
 * 需要通过 bean 名称“tx” 和方法名定位到Method,然后反射调用
 * 
 * 			<aop:before pointcut-ref="placeOrder" method="start" />
			<aop:after-returning pointcut-ref="placeOrder"	method="commit" />	
			<aop:after-throwing pointcut-ref="placeOrder" method = "rollback"/>
 * 
 * @author Administrator
 *
 */
public class MethodLocatingFactoryTest {

	@Test
	public void test() throws NoSuchMethodException, SecurityException {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("beansV5.xml");	
		reader.loadDefinitions(resource);
		
		MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
		methodLocatingFactory.setTargetBeanName("tx");
		methodLocatingFactory.setMethodName("start");
		methodLocatingFactory.setBeanFactory(factory);
		
		Method m = methodLocatingFactory.getObject();
		
		Assert.assertTrue(TransactionManager.class.equals(m.getDeclaringClass()));
		Assert.assertTrue(m.equals(TransactionManager.class.getMethod("start")));
	}

}
